package com.gmail.kidusmt.adegareporter.data;

import android.util.Log;

import com.gmail.kidusmt.adegareporter.util.App;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by KidusMT on 2/9/2018.
 */

public class AccidentRepo implements AccidentRepoContract {

    private static AccidentRepoContract repository;
    private List<Accident> accidents;

    Box<Accident> accidentBox = App.boxStore.boxFor(Accident.class);

    public static AccidentRepoContract getInstance(){
        if(repository == null){
            repository = new AccidentRepo();
        }
//        Log.e("--->",""+repository.getAccidents().size());
        return repository;
    }

    private AccidentRepo(){

        accidents = accidentBox.getAll();
    }

    @Override
    public List<Accident> getAccidents() {
        return accidents;
    }

    @Override
    public void addAccidents(Accident accident) {
        accidents.add(accident);
    }

    @Override
    public boolean isEmpty() {
        return accidents.isEmpty();
    }
}
