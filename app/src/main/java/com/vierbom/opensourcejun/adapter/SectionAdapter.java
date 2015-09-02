package com.vierbom.opensourcejun.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vierbom.opensourcejun.R;
import com.vierbom.opensourcejun.activity.NameActivity;
import com.vierbom.opensourcejun.model.Section;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vierbom on 15/8/31.
 */
public class SectionAdapter extends BaseAdapter {

    LayoutInflater mLayoutInflater;
    List<Section> sections;
    Activity activity;
    public SectionAdapter(Activity activity, List<Section> sections) {
        this.mLayoutInflater = LayoutInflater.from(activity);
        this.sections = sections;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public Object getItem(int i) {
        return sections.get(i);
    }

    @Override
    public long getItemId(int i) {
        return sections.get(i).hashCode();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder mViewHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.section_item, viewGroup, false);
            mViewHolder = new ViewHolder(view);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        mViewHolder.section.setText(sections.get(i).getSection());
        mViewHolder.section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(NameActivity.SECTION,sections.get(i).getSection());
                intent.setClass(activity, NameActivity.class);
                activity.startActivity(intent);
            }
        });

        return view;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'section_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.section)
        TextView section;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
