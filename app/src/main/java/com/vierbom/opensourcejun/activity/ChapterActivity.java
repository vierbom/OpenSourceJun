package com.vierbom.opensourcejun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.indris.material.RippleView;
import com.vierbom.opensourcejun.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChapterActivity extends ActionBarActivity {
    public static final String ID = "id";
    @Bind(R.id.widgets)
    RippleView widgets;
    @Bind(R.id.tools)
    RippleView tools;
    @Bind(R.id.projects)
    RippleView projects;
    @Bind(R.id.test_tools)
    RippleView testTools;
    @Bind(R.id.guys)
    RippleView guys;
    private boolean isclick = true;
    private Handler mHandler;
    private int mId;
    Runnable runnable = new Runnable() {
        public void run() {
            Intent intent = new Intent();
            intent.setClass(ChapterActivity.this, SectionActivity.class);
            intent.putExtra(ChapterActivity.ID, mId);
            startActivity(intent);
        }
    };
    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chapter);
        initToolbar();
        mHandler = new Handler();
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View view) {
                jump(view.getId());
            }
        };
        widgets.setOnClickListener(clickListener);
        tools.setOnClickListener(clickListener);
        projects.setOnClickListener(clickListener);
        testTools.setOnClickListener(clickListener);
        guys.setOnClickListener(clickListener);
    }

    private void jump(int id) {
        mId = id;
        if (isclick) {
            isclick = false;
            mHandler.postDelayed(runnable, 400);
        }
    }

    @Override
    protected void onResume() {
        isclick = true;
        super.onResume();
    }
}
