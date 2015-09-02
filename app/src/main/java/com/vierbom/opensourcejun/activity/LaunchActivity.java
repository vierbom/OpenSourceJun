package com.vierbom.opensourcejun.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vierbom.opensourcejun.R;
import com.vierbom.opensourcejun.dataprovider.NameProvider;
import com.vierbom.opensourcejun.dataprovider.SectionProvider;
import com.vierbom.opensourcejun.model.Name;
import com.vierbom.opensourcejun.model.Section;
import com.vierbom.opensourcejun.util.SystemUiHider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.LinkedList;


public class LaunchActivity extends Activity {

    private static final String INIT_DATABASE = "initDatabase";
    private static final boolean AUTO_HIDE = true;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final boolean TOGGLE_ON_CLICK = true;

    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    Handler mHideHandler = new Handler();
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    private SystemUiHider mSystemUiHider;
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(LaunchActivity.this).getBoolean(INIT_DATABASE, true)) {
            initDatabase();
        }
        setContentView(R.layout.activity_launch);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.dummy_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaunchActivity.this, ChapterActivity.class));
                finish();
            }
        });
    }

    private void initDatabase() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("section.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Type listType = new TypeToken<LinkedList<Section>>(){}.getType();
            Gson gson = new Gson();
            LinkedList<Section> sections = gson.fromJson(stringBuilder.toString(), listType);
            SectionProvider.getInstance().addToSectionTable(sections);

            InputStreamReader inputStreamReader2 = new InputStreamReader(getAssets().open("name.json"), "UTF-8");
            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
            String line2;
            StringBuilder stringBuilder2 = new StringBuilder();
            while ((line2 = bufferedReader2.readLine()) != null) {
                stringBuilder2.append(line2);
            }
            Type listType2 = new TypeToken<LinkedList<Name>>(){}.getType();
            Gson gson2 = new Gson();
            LinkedList<Name> names = gson2.fromJson(stringBuilder2.toString(), listType2);
            NameProvider.getInstance().addToNameTable(names);



        } catch (IOException e) {
            e.printStackTrace();
        }
        PreferenceManager.getDefaultSharedPreferences(LaunchActivity.this).edit().putBoolean(INIT_DATABASE, false).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(100);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
