package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp5_memo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_details, container, false);

        String memoName = getArguments().getString("memoName", "DefaultValue");
        Boolean memoStatus = getArguments().getBoolean("memoStatus", false);
        String memoDescription = getArguments().getString("memoDescription", "No description");

        /*ImageView checkImage = root.findViewById(R.id.image);
        if(memoStatus){
            checkImage.setImageResource(R.mipmap.doneimage);
        } else {
            checkImage.setImageResource(R.mipmap.notdoneimage);
        }
*/
        TextView name = root.findViewById(R.id.name);
        name.setText(memoName);

        TextView description = root.findViewById(R.id.description);
        description.setText(memoDescription);

        // Inflate the layout for this fragment
        return root;
    }
}
