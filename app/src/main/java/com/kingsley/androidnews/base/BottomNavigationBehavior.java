package com.kingsley.androidnews.base;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

/**
 * class name : BottomNavigationBehavior
 * created date : on 2018/1/29 10:46
 *
 * @author Kingsley
 * @version 1.0
 */

class BottomNavigationBehavior<V extends View> extends VerticalScrollingBehavior<V> {
    private static final Interpolator INTERPOLATOR = new LinearOutSlowInInterpolator();
    private int bottomNavHeight;
    private int defaultOffset;
    private boolean isTablet = false;

    private ViewPropertyAnimatorCompat mTranslationAnimator;
    private boolean hidden = false;
    private int mSnackbarHeight = -1;
    private final BottomNavigationBehavior.BottomNavigationWithSnackbar mWithSnackBarImpl =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                    new BottomNavigationBehavior.LollipopBottomNavWithSnackBarImpl() :
                    new BottomNavigationBehavior.PreLollipopBottomNavWithSnackBarImpl();
    private boolean mScrollingEnabled = true;

    BottomNavigationBehavior(int bottomNavHeight, int defaultOffset, boolean tablet) {
        this.bottomNavHeight = bottomNavHeight;
        this.defaultOffset = defaultOffset;
        isTablet = tablet;
    }

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        mWithSnackBarImpl.updateSnackbar(parent, dependency, child);
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public void onNestedVerticalOverScroll(CoordinatorLayout coordinatorLayout, V child, @ScrollDirection int direction, int currentOverScroll, int totalOverScroll) {
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, V child, View dependency) {
        updateScrollingForSnackbar(dependency, true);
        super.onDependentViewRemoved(parent, child, dependency);
    }

    private void updateScrollingForSnackbar(View dependency, boolean enabled) {
        if (!isTablet && dependency instanceof Snackbar.SnackbarLayout) {
            mScrollingEnabled = enabled;
        }
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        updateScrollingForSnackbar(dependency, false);
        bottomNavHeight = child.getHeight();
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onDirectionNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed, @ScrollDirection int scrollDirection) {
        handleDirection(child, scrollDirection);
    }

    private void handleDirection(V child, int scrollDirection) {
        if (!mScrollingEnabled) {
            return;
        }
        if (scrollDirection == ScrollDirection.SCROLL_DIRECTION_DOWN && hidden) {
            hidden = false;
            animateOffset(child, defaultOffset);
        } else if (scrollDirection == ScrollDirection.SCROLL_DIRECTION_UP && !hidden) {
            hidden = true;
            animateOffset(child, bottomNavHeight + defaultOffset);
        }
    }

    @Override
    protected boolean onNestedDirectionFling(CoordinatorLayout coordinatorLayout, V child, View target, float velocityX, float velocityY, @ScrollDirection int scrollDirection) {
        handleDirection(child, scrollDirection);
        return true;
    }

    private void animateOffset(final V child, final int offset) {
        ensureOrCancelAnimator(child);
        mTranslationAnimator.translationY(offset).start();
    }

    private void ensureOrCancelAnimator(V child) {
        if (mTranslationAnimator == null) {
            mTranslationAnimator = ViewCompat.animate(child);
            mTranslationAnimator.setDuration(300);
            mTranslationAnimator.setInterpolator(INTERPOLATOR);
        } else {
            mTranslationAnimator.cancel();
        }
    }


    void setHidden(@NonNull  V view, boolean bottomLayoutHidden) {
        if (!bottomLayoutHidden && hidden) {
            animateOffset(view, defaultOffset);
        } else if (bottomLayoutHidden && !hidden) {
            animateOffset(view,  bottomNavHeight + defaultOffset);
        }
        hidden = bottomLayoutHidden;
    }


    static <V extends View> BottomNavigationBehavior<V> from(@NonNull V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();

        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }

        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params)
                .getBehavior();

        if (behavior instanceof BottomNavigationBehavior) {
            // noinspection unchecked
            return (BottomNavigationBehavior<V>) behavior;
        }

        throw new IllegalArgumentException("The view is not associated with BottomNavigationBehavior");
    }

    private interface BottomNavigationWithSnackbar {
        void updateSnackbar(CoordinatorLayout parent, View dependency, View child);
    }


    private class PreLollipopBottomNavWithSnackBarImpl implements BottomNavigationWithSnackbar {

        @Override
        public void updateSnackbar(CoordinatorLayout parent, View dependency, View child) {
            if (!isTablet && dependency instanceof Snackbar.SnackbarLayout) {
                if (mSnackbarHeight == -1) {
                    mSnackbarHeight = dependency.getHeight();
                }
                if (ViewCompat.getTranslationY(child) != 0) return;
                int targetPadding = bottomNavHeight + mSnackbarHeight - defaultOffset;

                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) dependency.getLayoutParams();
                layoutParams.bottomMargin = targetPadding;
                child.bringToFront();
                child.getParent().requestLayout();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    ((View) child.getParent()).invalidate();
                }

            }
        }
    }

    private class LollipopBottomNavWithSnackBarImpl implements BottomNavigationWithSnackbar {
        @Override
        public void updateSnackbar(CoordinatorLayout parent, View dependency, View child) {
            if (!isTablet && dependency instanceof Snackbar.SnackbarLayout) {
                if (mSnackbarHeight == -1) {
                    mSnackbarHeight = dependency.getHeight();
                }
                if (ViewCompat.getTranslationY(child) != 0) return;
                int targetPadding = (mSnackbarHeight + bottomNavHeight - defaultOffset);
                dependency.setPadding(dependency.getPaddingLeft(),
                        dependency.getPaddingTop(), dependency.getPaddingRight(), targetPadding
                );
            }
        }
    }
}