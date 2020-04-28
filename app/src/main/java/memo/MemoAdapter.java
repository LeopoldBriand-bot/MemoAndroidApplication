package memo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp5_memo.DetailsActivity;
import com.example.tp5_memo.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Collections;
import java.util.List;

import Fragments.DetailsFragment;
import cz.msebera.android.httpclient.Header;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {
    private List<Memo> listMemos;
    public AppCompatActivity activity;
    public MemoAdapter(List<Memo> listMemos, AppCompatActivity activity){
        this.listMemos = listMemos;
        this.activity = activity;
    }
    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCourse = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo, parent, false);
        return new MemoViewHolder(viewCourse);
    }
    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position){
        holder.textViewTextMemo.setText(listMemos.get(position).text);
    }
    @Override
    public int getItemCount()   {
        return listMemos.size();
    }

    public class MemoViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTextMemo;
        public CheckBox checkboxViewMemo;
        public MemoViewHolder(final View itemView) {
            super(itemView);
            textViewTextMemo = itemView.findViewById(R.id.text_memo);
            textViewTextMemo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Memo memoDTO = listMemos.get(getAdapterPosition());
                    int orientation = view.getResources().getConfiguration().orientation;
                    int screenSize = view.getResources().getConfiguration().screenLayout &
                            Configuration.SCREENLAYOUT_SIZE_MASK;
                    if (orientation == Configuration.ORIENTATION_PORTRAIT && (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL || screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL)) {
                        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                        intent.putExtra("memoName", memoDTO.text);
                        intent.putExtra("memoDescription", memoDTO.description);
                        intent.putExtra("memoStatus", memoDTO.status);
                        view.getContext().startActivity(intent);
                    } else {
                        DetailsFragment fragment = new DetailsFragment();
                        Bundle bundle = new Bundle();
                        fragment.setArguments(bundle);
                        bundle.putString("memoName", memoDTO.text);
                        bundle.putString("memoDescription", memoDTO.description);

                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentParent, fragment, "details");
                        fragmentTransaction.commit();
                    }
                }
            });
            checkboxViewMemo = itemView.findViewById(R.id.checkbox_memo);
            checkboxViewMemo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Memo memo = listMemos.get(getAdapterPosition());
                    memo.changeStatus();
                    onItemCheck(memo);
                }
            });
        }
    }

    public boolean onItemMove(int positionDebut, int positionFin) {
        Collections.swap(listMemos, positionDebut, positionFin);
        notifyItemMoved(positionDebut, positionFin);
        return true;
    }

    public void onItemDismiss(int position) {
        Memo memo = listMemos.get(position);
        AppDatabaseHelper.getDatabase(this.activity).memoDAO().delete(memo);
        if (position > -1) {
            listMemos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void onItemCheck(Memo memo){
        AppDatabaseHelper.getDatabase(this.activity).memoDAO().update(memo);
    }
}
