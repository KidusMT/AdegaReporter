package com.gmail.kidusmt.adegareporter.ui.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.kidusmt.adegareporter.R;
import com.gmail.kidusmt.adegareporter.base.view.BaseFragment;

public class FragmentRecord extends BaseFragment {

    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_record, container, false);

        recyclerView = v.findViewById(R.id.recycler_view_record);

        return v;
    }


}
