package com.gmail.kidusmt.adegareporter.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.kidusmt.adegareporter.R;
import com.gmail.kidusmt.adegareporter.base.view.BaseFragment;
import com.gmail.kidusmt.adegareporter.data.Accident;
import com.gmail.kidusmt.adegareporter.util.App;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class FragmentHome extends BaseFragment {

    private RecyclerView recyclerView;
    private AccidentAdapter adapter;

    Box<Accident> accidentBox = App.boxStore.boxFor(Accident.class);

    public List<Accident> accidents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accidents = accidentBox.getAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container, false);

        recyclerView = v.findViewById(R.id.recycler_view_home);

        adapter = new AccidentAdapter(getContext(),accidents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

//        AccidentAdapter

        if(accidents.size()!=0){
            recyclerView.setAdapter(adapter);
        }


        return v;
    }


}
