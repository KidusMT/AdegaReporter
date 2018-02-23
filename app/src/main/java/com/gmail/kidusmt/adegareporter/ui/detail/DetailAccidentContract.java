package com.gmail.kidusmt.adegareporter.ui.detail;

/**
 * Created by KidusMT on 2/9/2018.
 */

public interface DetailAccidentContract {

    interface Presenter{

        void onBackPressed();

    }

    interface View{

        void showDetailAccidentActivity();

        void closeDetailAccidentActivity();
    }
}
