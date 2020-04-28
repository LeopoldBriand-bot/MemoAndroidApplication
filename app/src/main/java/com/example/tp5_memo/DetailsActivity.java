package com.example.tp5_memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import Fragments.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String memoName = getIntent().getStringExtra("memoName");
        String memoDescription = getIntent().getStringExtra("memoDescription");
        Boolean memoStatus = getIntent().getBooleanExtra("memoStatus", false);

        DetailsFragment fragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        bundle.putString("memoName", memoName);
        bundle.putString("memoDescription", memoDescription);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentParent, fragment, "details");
        fragmentTransaction.commit();
    }
}
