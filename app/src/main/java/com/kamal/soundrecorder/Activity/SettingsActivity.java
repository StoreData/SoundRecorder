package com.kamal.soundrecorder.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.kamal.soundrecorder.R;
import com.kamal.soundrecorder.Fragments.SettingsFragment;

/**
 * Created by Daniel on 5/22/2017.
 */

public class SettingsActivity extends BaseActivity {
    @Override
    protected void initViews() {
        setupToolbar("Settings");
        enableNavigation();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .commit();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preferences;
    }
}
