package com.yalantis.euclid.library;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;
import java.util.Map;

public class EuclidListAdapter extends ArrayAdapter<Map<String, Object>> {

    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_NAME = "name";
    public static final String EXAMPLE = "example";
    public static final String ADDRESS = "address";
    public static final String INTRODUCE = "introduce";

    private final LayoutInflater mInflater;
    private List<Map<String, Object>> mData;

    public EuclidListAdapter(Context context, int layoutResourceId, List<Map<String, Object>> data) {
        super(context, layoutResourceId, data);
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mViewOverlay = convertView.findViewById(R.id.view_avatar_overlay);
            viewHolder.mListItemAvatar = (SimpleDraweeView) convertView.findViewById(R.id.image_view_avatar);
            viewHolder.mListItemName = (TextView) convertView.findViewById(R.id.text_view_name);
            viewHolder.mListItemDescription = (TextView) convertView.findViewById(R.id.text_view_example);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Uri uri = Uri.parse((String) mData.get(position).get(KEY_AVATAR));
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .build();
        viewHolder.mListItemAvatar.setController(controller);
        viewHolder.mListItemName.setText(mData.get(position).get(KEY_NAME).toString().toUpperCase());
        viewHolder.mListItemDescription.setText("APP示例："+(String) mData.get(position).get(EXAMPLE));
        viewHolder.mViewOverlay.setBackground(EuclidActivity.sOverlayShape);

        return convertView;
    }

    static class ViewHolder {
        View mViewOverlay;
        SimpleDraweeView mListItemAvatar;
        TextView mListItemName;
        TextView mListItemDescription;
    }
}
