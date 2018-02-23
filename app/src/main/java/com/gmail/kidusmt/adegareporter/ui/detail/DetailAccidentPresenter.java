package com.gmail.kidusmt.adegareporter.ui.detail;

import com.gmail.kidusmt.adegareporter.data.AccidentRepo;

/**
 * Created by KidusMT on 2/9/2018.
 */

public class DetailAccidentPresenter implements DetailAccidentContract.Presenter {

    DetailAccidentContract.View view;

    public DetailAccidentPresenter(DetailAccidentContract.View view){
        this.view = view;
        this.repository = AccidentRepo.getInstance();
    }

    @Override
    public void onBackPressed() {
        view.closeDetailAccidentActivity();
    }
}
