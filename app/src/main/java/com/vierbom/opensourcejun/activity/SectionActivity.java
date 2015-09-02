package com.vierbom.opensourcejun.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vierbom.opensourcejun.R;
import com.vierbom.opensourcejun.adapter.SectionAdapter;
import com.vierbom.opensourcejun.dataprovider.SectionProvider;
import com.vierbom.opensourcejun.model.Section;
import com.vierbom.opensourcejun.widget.PullSeparateListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SectionActivity extends ActionBarActivity {
    @Bind(R.id.section_lv)
    PullSeparateListView sectionLv;

    private void initToolbar(String title) {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
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
        setContentView(R.layout.activity_section);
        ButterKnife.bind(this);
        String value = "";
        switch (getIntent().getIntExtra(ChapterActivity.ID, 0)) {
            case R.id.widgets:
                value = "个性化控件篇";
                break;
            case R.id.tools:
                value = "工具库篇";
                break;
            case R.id.projects:
                value = "个性化控件篇";
                break;
            case R.id.test_tools:
                value = "个性化控件篇";
                break;
            case R.id.guys:
                value = "个性化控件篇";
                break;
        }
        initToolbar(value);

        final List<Section> sections = SectionProvider.getInstance().getSections(value);
//        for (Iterator iterator = sections.iterator(); iterator.hasNext(); ) {
//            Section user = (Section) iterator.next();
//            Log.i("TTT", user.getSection());
//        }
        SectionAdapter sectionAdapter = new SectionAdapter(this, sections);
        sectionLv.setAdapter(sectionAdapter);
    }
}
