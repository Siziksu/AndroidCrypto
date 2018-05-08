package com.siziksu.crypto.ui.common;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.siziksu.crypto.R;

/**
 * Helper class to generate the snackbars of the application.
 */
public class SnackbarHelper {

    private SnackbarHelper() {}

    /**
     * Make an infinite Snackbar to display a message.
     * <p>Snackbar will try and find a parent view to hold Snackbar's view from the value given
     * to {@code view}. Snackbar will walk up the view tree trying to find a suitable parent,
     * which is defined as a {@link CoordinatorLayout} or the window decor's content view,
     * whichever comes first.
     * <p>Having a {@link CoordinatorLayout} in your view hierarchy allows Snackbar to enable
     * certain features, such as swipe-to-dismiss and automatically moving of widgets like
     * {@link FloatingActionButton}.
     *
     * @param view    The view to find a parent from
     * @param message The text to show.  Can be formatted text
     */
    public static void showInfiniteSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.action_ok, onClick -> {});
        snackbar.show();
    }
}
