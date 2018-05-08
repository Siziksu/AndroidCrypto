package com.siziksu.crypto.ui.router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.siziksu.crypto.R;
import com.siziksu.crypto.common.Constants;
import com.siziksu.crypto.ui.view.detail.DetailActivity;
import com.siziksu.crypto.ui.model.Coin;
import com.siziksu.crypto.ui.view.portfolio.PortfolioActivity;

import javax.inject.Inject;

/**
 * Class for the navigation of the application.
 */
public final class Router implements RouterContract {

    @Inject
    RouterHelper routerHelper;

    public Router(RouterHelper routerHelper) {
        this.routerHelper = routerHelper;
    }

    @Override
    public void replaceFragment(AppCompatActivity activity, int view, Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(view, fragment, fragmentTag).commit();
    }

    @Override
    public void goToDetail(AppCompatActivity activity, Coin coin) {
        routerHelper.putParcelable(Constants.EXTRAS_COIN, coin);
        routerHelper.route(activity, DetailActivity.class);
        activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    @Override
    public void goToPortfolio(AppCompatActivity activity) {
        routerHelper.route(activity, PortfolioActivity.class);
        activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }
}
