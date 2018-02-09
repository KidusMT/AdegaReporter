package com.gmail.kidusmt.adegareporter.ui.home;

import com.gmail.kidusmt.adegareporter.data.Accident;

import java.util.List;

/**
 * Created by KidusMT on 2/9/2018.
 */

public interface HomeContract {

    interface Presenter{

        /**
         * Called when delete icon clicked on the cardView
         */
        void onDeleteRecordClicked();

        /**
         * Called when the Add Accident FAB is clicked.
         */
        void onAddAccidentClicked();

        /**
         * Called when a single Accident has been clicked, with the given position.
         */
        void onAccidentClicked(int position);

        /**
         * Called by the View to indicate to the presenter that the View has finished loading
         */
        void start();
    }

    interface View{

        /**
         * Opens the Add Accident screen
         */
        void openAddAccidentScreen();

        /**
         * Called by the presenter, with the details of the Accident to show on the screen, when a Accident
         * was clicked.
         */
        void showAccidentDetails(String detail);

        /**
         * Show a List of Accidents on the home screen
         */
        void showAccidents(List<Accident> accidents);

        /**
         * Refresh the list of Accidents on the home screen, so that if something new was added it is
         * visible
         */
        void refreshList();

        /**
         * Show the no Accidents label (when we have Accidents to show on the screen).
         */
        void showNoRecordsLabel();

        /**
         * Hide the no Accidents label (when we have no Accidents to show on the screen).
         */
        void hideNoRecordsLabel();
    }
}
