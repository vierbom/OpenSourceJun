package com.vierbom.opensourcejun.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.vierbom.opensourcejun.R;
import com.vierbom.opensourcejun.dataprovider.NameProvider;
import com.vierbom.opensourcejun.model.Name;
import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameActivity extends EuclidActivity {
    public static final String SECTION="section";
    private List<Name>names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String section=getIntent().getStringExtra(SECTION);
        names= NameProvider.getInstance().getNames(section);
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        mTextViewEuclidTitle.setText(section);

        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(address);
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();


        for (int i = 0; i < names.size(); i++) {
            profileMap = new HashMap<>();
            String pic=names.get(i).getPic();
            if (pic==null)pic="asset:///googlelookevolved.gif";
//                pic="res:///"+R.mipmap.ic_launcher;
            profileMap.put(EuclidListAdapter.KEY_AVATAR, pic);
            profileMap.put(EuclidListAdapter.KEY_NAME, names.get(i).getName());
            profileMap.put(EuclidListAdapter.EXAMPLE, names.get(i).getExample());
            profileMap.put(EuclidListAdapter.ADDRESS, names.get(i).getAddress());
            profileMap.put(EuclidListAdapter.INTRODUCE, names.get(i).getIntroduce());
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }

}
