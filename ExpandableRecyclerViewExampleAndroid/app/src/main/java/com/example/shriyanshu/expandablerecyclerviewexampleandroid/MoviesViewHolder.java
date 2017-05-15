package com.example.shriyanshu.expandablerecyclerviewexampleandroid;

import android.view.View;
import android.widget.TextView;

public class MoviesViewHolder extends ChildViewHolder {

    private TextView mMoviesTextView;

    public MoviesViewHolder(View itemView) {
        super(itemView);
        mMoviesTextView = (TextView) itemView.findViewById(R.id.tv_movies);
    }

    public void bind(Movies movies) {
        mMoviesTextView.setText(movies.getName());
    }
}
