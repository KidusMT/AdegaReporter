package com.gmail.kidusmt.adegareporter.ui.home;

import android.util.Log;

import com.gmail.kidusmt.adegareporter.data.Accident;
import com.gmail.kidusmt.adegareporter.data.AccidentRepo;
import com.gmail.kidusmt.adegareporter.data.AccidentRepoContract;

import java.util.List;

/**
 * Created by KidusMT on 2/9/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    private AccidentRepoContract repository;

    public HomePresenter(HomeContract.View view){
        this.view = view;

        repository = AccidentRepo.getInstance();
//        Log.e("--->HomePresenter",""+repository.getAccidents().size());
    }

    @Override
    public void onDeleteRecordClicked() {
        //TODO I Have to implement the deletion of the row here
    }

    @Override
    public void onAddAccidentClicked() {
        view.openAddAccidentScreen();
    }

    @Override
    public void onAccidentClicked(int position) {
        Accident accident = repository.getAccidents().get(position);

        //TODO should change the single string into String[]
        view.showAccidentDetails(accident);
    }

    @Override
    public void start() {
        List<Accident> accidents = repository.getAccidents();
        Log.e("--->Start",""+repository.getAccidents().size());

        //TODO because I don't have the labels in the home fragment
//        if(accidents.isEmpty()){
//            view.showNoRecordsLabel();
//        }else{
//            view.hideNoRecordsLabel();
//        }

        view.showAccidents(accidents);
    }
}
