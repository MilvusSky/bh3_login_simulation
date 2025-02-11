package com.github.haocen2004.login_simulation.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;

import com.github.haocen2004.login_simulation.data.dialog.DialogLiveData;
import com.github.haocen2004.login_simulation.utils.Logger;
import com.github.haocen2004.login_simulation.utils.PmsHooker;
import com.github.haocen2004.login_simulation.utils.ThemeUtils;

import rikka.material.app.MaterialActivity;

public class BaseActivity extends MaterialActivity {

    private final String TAG = "activityManager";
    ActivityManager activityManager = ActivityManager.getInstance();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.addActivity(this);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        Logger.d(TAG, "onCreate: " + getClass().getName());
    }

    @Override
    protected void onPause() {
        Logger.d(TAG, "onPause: " + getClass().getName());
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume: " + getClass().getName());
        activityManager.addActivity(this);
    }

    @Override
    protected void onStop() {
        Logger.d(TAG, "onStop: " + getClass().getName());
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        activityManager.addActivity(this);
        Logger.d(TAG, "onStart: " + getClass().getName());
    }

    @Override
    protected void onDestroy() {
        Logger.d(TAG, "onDestroy: " + getClass().getName());
        activityManager.removeActivity(this);
        DialogLiveData.getINSTANCE().notifyDialog();
        super.onDestroy();
    }

    @NonNull
    @Override
    public String getOpPackageName() {
        return PmsHooker.getPackageNameFilter(super.getOpPackageName());
    }

    @Override
    public String getPackageName() {
        return PmsHooker.getPackageNameFilter(super.getPackageName());
    }

    @Override
    public void onApplyUserThemeResource(@NonNull Resources.Theme theme, boolean isDecorView) {
        super.onApplyUserThemeResource(theme, isDecorView);
        theme.applyStyle(ThemeUtils.getNightThemeStyleRes(this), true);
    }


    @Override
    public void onApplyTranslucentSystemBars() {
        super.onApplyTranslucentSystemBars();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
    }
}
