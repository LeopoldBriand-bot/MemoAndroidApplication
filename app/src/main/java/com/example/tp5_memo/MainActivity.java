package com.example.tp5_memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import memo.AppDatabaseHelper;
import memo.ItemTouchHelperCallBack;
import memo.Memo;
import memo.MemoAdapter;

public class MainActivity extends AppCompatActivity {

    List<Memo> listMemos;
    EditText newMemoText;
    Button createMemoButton;
    MemoAdapter memosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.list_memo);
        recyclerView.setHasFixedSize(true);


        newMemoText = (EditText) findViewById(R.id.editText);
        createMemoButton = (Button) findViewById(R.id.button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listMemos = AppDatabaseHelper.getDatabase(this).memoDAO().getListMemos();

        memosAdapter = new MemoAdapter(listMemos, this);
        recyclerView.setAdapter(memosAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallBack(memosAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("listMemo", Parcels.wrap(listMemos));
        super.onSaveInstanceState(outState);
    }
    public void createMemo (View view) {
        Memo memoDTO = new Memo (newMemoText.getText().toString(), false, "Default description");
        listMemos.add(memoDTO);
        memosAdapter.notifyItemInserted(listMemos.size());
        AppDatabaseHelper.getDatabase(this).memoDAO().insert(memoDTO);
    }
}
