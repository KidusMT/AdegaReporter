//package com.gmail.kidusmt.adegareporter.ui.welcome;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.gmail.kidusmt.
//import com.gmail.kidusmt.adegareporter.base.view.BaseFragment;
//import com.squareup.picasso.Picasso;
//
///**
// * Fragment used by the tour screen, for displaying the images.
// */
//public class WelcomeFragment extends BaseFragment {
//
//    private static final String ARG_POSITION = "POSITION";
//
//    /**
//     * Static method for creating a Fragment object.
//     *
//     * @param position int value used to determine which image to show in the fragment.
//     * @return created TourFragment
//     */
//    public static WelcomeFragment newInstance(int position) {
//        // Create a new Fragment
//        WelcomeFragment fragment = new WelcomeFragment();
//
//        // Create a Bundle arg value and attach an int position to it (for the image)
//        Bundle args = new Bundle();
//        args.putInt(ARG_POSITION, position);
//
//        // Add the Bundle arg value to the Fragment and return the fragment
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_tour, container, false);
//
//        Bundle args = getArguments();
//        if (args == null) throw new NullPointerException("Bundle args cannot be null");
//
//        final int position = args.getInt(ARG_POSITION, 0);
//
//        ImageView tourImageView = root.findViewById(R.id.tourFragmentImageView);
//        int image;
//        switch (position) {
//            case 0:
//                image = R.drawable.splash_logo_icon;
//                break;
//            default:
//                image = R.drawable.cool_image_01;
//        }
//
//        if (position == 0) {
//            tourImageView.setImageResource(R.drawable.splash_logo_icon);
//        } else {
//            Picasso.with(getContext())
//                    .load(image)
//                    .fit()
//                    .into(tourImageView);
//
//        }
//
//        root.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (position != TourActivity.PAGE_COUNT - 1) return;
//                startActivity(new Intent(getActivity(), HomeActivity.class));
//                getActivity().finish();
//            }
//        });
//
//        return root;
//    }
//}
