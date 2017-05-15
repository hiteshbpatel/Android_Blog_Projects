package com.zhuinden.realmbookexample.paths.books;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhuinden.realmbookexample.R;
import com.zhuinden.realmbookexample.data.entity.Sari;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;


public class SarisAdapter extends RealmRecyclerViewAdapter<Sari, SarisAdapter.SariViewHolder> {
    final SarisPresenter sarisPresenter;

    public SarisAdapter(Context context, RealmResults<Sari> saris) {
        super(context, saris, true);
        this.sarisPresenter = SarisPresenter.getService(context);
    }

    // create new views (invoked by the layout manager)
    @Override
    public SariViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        return new SariViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saris, parent, false));
    }

    @Override
    public void onBindViewHolder(SariViewHolder holder, final int position) {
        // get the article
        final Sari sari = getItem(position);
        if(sari == null) {
            return;
        } else {
            holder.bind(sari);
        }
    }

    public static class SariViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_saris)
        CardView card;

        @BindView(R.id.text_saris_title)
        TextView textTitle;

        @BindView(R.id.text_saris_description)
        TextView textDescription;

        @BindView(R.id.text_price)
        TextView textPrice;

        @BindView(R.id.image_background)
        ImageView imageBackground;

        final Context context;
        final SarisPresenter sarisPresenter;

        public SariViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);
            this.context = itemView.getContext();
            this.sarisPresenter = SarisPresenter.getService(context);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Sari sari) {
            // cast the generic view holder to our specific one
            // set the title and the snippet
            final long id = sari.getId();
            textTitle.setText(sari.getTitle());
            textDescription.setText(sari.getDescription());
            textPrice.setText(sari.getPrice());

            // load the background image
            if (sari.getImageUrl() != null) {
                Glide.with(context)
                        .load(sari.getImageUrl())
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageBackground);
            }

            //remove single match from realm
            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    sarisPresenter.deleteSariById(id);
                    return false;
                }
            });

            //update single match from realm
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sarisPresenter.showEditDialog(sari);
                }
            });
        }
    }
}
