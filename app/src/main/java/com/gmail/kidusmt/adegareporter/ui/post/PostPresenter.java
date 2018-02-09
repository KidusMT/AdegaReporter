package com.gmail.kidusmt.adegareporter.ui.post;

import com.gmail.kidusmt.adegareporter.data.Accident;
import com.gmail.kidusmt.adegareporter.data.AccidentRepo;
import com.gmail.kidusmt.adegareporter.data.AccidentRepoContract;

/**
 * Created by KidusMT on 2/9/2018.
 */

public class PostPresenter implements PostContract.Presenter{

    PostContract.View view;
    private AccidentRepoContract repository;

    public PostPresenter(PostContract.View view){
        this.view = view;
        this.repository = AccidentRepo.getInstance();
    }

    @Override
    public void onSaveClicked(Accident accident) {
        repository.addAccidents(accident);

        view.closeAddAccident();
    }

    @Override
    public void onBackPressed() {
        view.closeAddAccident();
    }
}
