package com.example.shriyanshu.expandablerecyclerviewexampleandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private MovieCategoryAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Movies  movie_one = new Movies("The Shawshank Redemption");
        Movies  movie_two  = new Movies("The Godfather");
        Movies  movie_three = new Movies("The Dark Knight");
        Movies movie_four  = new Movies("Schindler's List ");
        Movies movie_five = new Movies("12 Angry Men ");
        Movies movie_six = new Movies("Pulp Fiction");
        Movies movie_seven = new Movies("The Lord of the Rings: The Return of the King");
        Movies movie_eight = new Movies("The Good, the Bad and the Ugly");
        Movies movie_nine = new Movies("Fight Club");
        Movies movie_ten = new Movies("Star Wars: Episode V - The Empire Strikes");
        Movies movie_eleven = new Movies("Forrest Gump");
        Movies movie_tweleve = new Movies("Inception");

        MovieCategory molvie_category_one = new MovieCategory("Drama", Arrays.asList(movie_one, movie_two, movie_three, movie_four));
        MovieCategory molvie_category_two = new MovieCategory("Action", Arrays.asList(movie_five, movie_six, movie_seven,movie_eight));
        MovieCategory molvie_category_three = new MovieCategory("History", Arrays.asList(movie_nine, movie_ten, movie_eleven,movie_tweleve));
        MovieCategory molvie_category_four = new MovieCategory("Thriller", Arrays.asList(movie_one, movie_five, movie_nine,movie_tweleve));

        final List<MovieCategory> movieCategories = Arrays.asList(molvie_category_one,  molvie_category_two, molvie_category_three,molvie_category_four);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new MovieCategoryAdapter(this, movieCategories);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                MovieCategory expandedMovieCategory = movieCategories.get(position);

                String toastMsg = getResources().getString(R.string.expanded, expandedMovieCategory.getName());
                Toast.makeText(MainActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                MovieCategory collapsedMovieCategory = movieCategories.get(position);

                String toastMsg = getResources().getString(R.string.collapsed, collapsedMovieCategory.getName());
                Toast.makeText(MainActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}