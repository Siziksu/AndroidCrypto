package com.siziksu.crypto.ui.router;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.siziksu.crypto.presenter.model.Coin;

public interface RouterContract {

    /**
     * Replaces a fragment.
     *
     * @param activity    the activity
     * @param view        the layout id in which will be replaced the fragment
     * @param fragment    the fragment
     * @param fragmentTag the tag for the fragment
     */
    void replaceFragment(AppCompatActivity activity, int view, Fragment fragment, String fragmentTag);

    /**
     * This method will navigate to the DetailActivity.
     *
     * @param activity the activity from where we go to the DetailActivity
     * @param coin     the coin parcelable object that will be sent to the DetailActivity
     */
    void goToDetail(AppCompatActivity activity, Coin coin);

    /**
     * This method will navigate to the PortfolioActivity.
     *
     * @param activity the activity from where we go to the DetailActivity
     */
    void goToPortfolio(AppCompatActivity activity);
}
