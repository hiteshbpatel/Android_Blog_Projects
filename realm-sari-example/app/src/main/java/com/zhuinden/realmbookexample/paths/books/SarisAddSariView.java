package com.zhuinden.realmbookexample.paths.books;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuinden.realmbookexample.R;
import com.zhuinden.realmbookexample.data.entity.Sari;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class SarisAddSariView extends LinearLayout implements SarisActivity.DialogContract {
    public SarisAddSariView(Context context) {
        super(context);
    }

    public SarisAddSariView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SarisAddSariView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public SarisAddSariView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    String title;
    String description;
    String price;
    String thumbnail;

    @BindView(R.id.title)
    EditText textTitle;

    @BindView(R.id.description)
    EditText textDescription;

    @BindView(R.id.price)
    EditText textPrice;

    @BindView(R.id.thumbnail)
    EditText textThumbnail;

    @OnTextChanged(R.id.title)
    public void titleChanged(CharSequence _title) {
        title = _title.toString();
    }

    @OnTextChanged(R.id.description)
    public void descriptionChanged(CharSequence _description) {
        description = _description.toString();
    }

    @OnTextChanged(R.id.price)
    public void priceChanged(CharSequence _price) {
        description = _price.toString();
    }

    @OnTextChanged(R.id.thumbnail)
    public void thumbnailChanged(CharSequence _thumbnail) {
        thumbnail = _thumbnail.toString();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public void bind(Sari sari) {
        String _title = sari.getTitle();
        String _description = sari.getDescription();
        String _price = sari.getPrice();
        String _thumbnail = sari.getImageUrl();
        textTitle.setText(_title);
        textDescription.setText(_description);
        textPrice.setText(_price);
        textThumbnail.setText(_thumbnail);
        title = _title;
        description = _description;
        price = _price;
        thumbnail = _thumbnail;
    }
}
