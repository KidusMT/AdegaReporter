package com.gmail.kidusmt.adegareporter.ui.post;

import com.gmail.kidusmt.adegareporter.data.Accident;

/**
 * Created by KidusMT on 2/9/2018.
 */

public interface PostContract {

    interface Presenter{
        /**
         * for saving the new accident record to the database
         * @param accident
         */
        void onSaveClicked(Accident accident);

        /**
         * for closing the addAccident activity
         */
        void onBackPressed();
    }

    interface View{

        /**
         * for closing the addAccident activity
         */
        void closeAddAccident();
    }

}
