package com.example.universalimageloaderexampleandroid;

import java.util.ArrayList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class UniversalImageAdapter extends ArrayAdapter<UniversalImageItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<UniversalImageItem> mGridData = new ArrayList<UniversalImageItem>();

    public UniversalImageAdapter(Context mContext, int layoutResourceId, ArrayList<UniversalImageItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */
    public void setGridData(ArrayList<UniversalImageItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) row.findViewById(R.id.iv_Grid);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        UniversalImageItem item = mGridData.get(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
        				.resetViewBeforeLoading(true)
        				.showImageForEmptyUri(R.drawable.placeholder)
        				.showImageOnFail(R.drawable.error)
        				.showImageOnLoading(R.drawable.placeholder).build();
        imageLoader.displayImage(item.getImage(), holder.imageView, options);
        return row;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}

