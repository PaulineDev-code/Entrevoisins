package com.openclassrooms.entrevoisins.ui.detail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;



public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout backGround = findViewById(R.id.toolbar_layout);
        long id = getIntent().getLongExtra("id", -1);
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbourbyid(id);
        getSupportActionBar().setTitle(neighbour.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        boolean isFav = DI.getNeighbourApiService().isFavorite(neighbour);


        Glide.with(this).asBitmap().load(neighbour.getAvatarUrl()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                backGround.setBackground(new BitmapDrawable(getResources() , resource));
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        } );


        TextView DetailName = findViewById(R.id.name);
        DetailName.setText(neighbour.getName());
        DetailName.setTextSize(25);
        DetailName.setTextColor(0xFF000000);

        TextView DetailAboutMeTitle = findViewById(R.id.aboutMeTitle);
        DetailAboutMeTitle.setText("À propos de moi");
        DetailAboutMeTitle.setTextSize(25);
        DetailAboutMeTitle.setTextColor(0xFF000000);

        TextView DetailAboutMe = findViewById(R.id.aboutMe);
        DetailAboutMe.setText(neighbour.getAboutMe());

        TextView DetailAddress = findViewById(R.id.address);
        DetailAddress.setText(neighbour.getAddress());

        TextView DetailPhone = findViewById(R.id.phoneNumber);
        DetailPhone.setText(neighbour.getPhoneNumber());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (isFav) { fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_white_24dp));}
        else {fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_white_24dp));}
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFavOnClick = DI.getNeighbourApiService().isFavorite(neighbour);
                if (isFavOnClick) {
                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    DI.getNeighbourApiService().deleteFavorite(neighbour);
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_white_24dp));
                }
                else {
                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    DI.getNeighbourApiService().addFavorite(neighbour);
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_white_24dp));
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = super.onSupportNavigateUp();
        finish();
        return b;
    }
}
