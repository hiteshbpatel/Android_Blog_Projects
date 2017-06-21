package com.vigneshtraining.assignment102;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vimadhavan on 4/18/2017.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder> {

    private String[] itemData;
    private MyClickListener myClickListener;

    public MyRecycleAdapter(String[] itemData, MyClickListener myClickListener) {
        this.itemData = itemData;
        this.myClickListener = myClickListener;
    }

    public String[] getItemData() {
        return itemData;
    }





    @Override
    public MyRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView,myClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecycleAdapter.ViewHolder holder, int position) {
        holder.txtViewTitle.setText(itemData[position]);
    }

    @Override
    public int getItemCount() {
        return itemData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtViewTitle;
        private MyClickListener myClickListener;


        public ViewHolder(View itemLayoutView,MyClickListener myClickListener) {
            super(itemLayoutView);
            this.myClickListener=myClickListener;
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
