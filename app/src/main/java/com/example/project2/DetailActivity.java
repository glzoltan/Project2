package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

public class DetailActivity extends AppCompatActivity {
    TextView title,description, rating,date;
    ImageView imageView;
    public static final String SHARED_PREFS="sharedPrefs";
    private DbHelper db;
    private Movie favorite;
    private final AppCompatActivity activity =DetailActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title=findViewById(R.id.movietitleview);
        description=findViewById(R.id.descriptionview);
        rating=findViewById(R.id.ratingview);
        date=findViewById(R.id.dateview);
        imageView=findViewById(R.id.imageView);
        Intent intent=getIntent();
        String tit=getIntent().getExtras().getString("original_title");
        String thumbnail="https://image.tmdb.org/t/p/w500"+getIntent().getExtras().getString("poster_path");
        String desc=getIntent().getExtras().getString("overview");
        String rat=String.valueOf(getIntent().getExtras().getString("vote_average"));
        String dte=getIntent().getExtras().getString("release_date");
        Glide.with(this).load(thumbnail).placeholder(R.drawable.ic_local_movies_black_24dp).into(imageView);
        title.setText(tit);
        description.setText(desc);
        rating.setText(rat);
        date.setText(dte);
        MaterialFavoriteButton materialFavoriteButton=findViewById(R.id.favorite_button);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        materialFavoriteButton.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener(){
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite){
                        if(favorite){
                            /*SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE).edit();
                            editor.putBoolean("Favorite Added",true);
                            editor.commit();*/
                            saveFavorite();
                            Toast toast = Toast.makeText(getApplicationContext() , "Added to favorite", Toast.LENGTH_SHORT);
                            toast.show();

                        }
                        else
                        {
                            int movie_id=getIntent().getExtras().getInt("movie_id");
                            String id=String.valueOf(movie_id);
                            db=new DbHelper(DetailActivity.this);
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            String user = sharedPreferences.getString("username","");
                            Toast toast = Toast.makeText(getApplicationContext() , user, Toast.LENGTH_SHORT);
                            toast.show();
                            db.deleteFavourite(movie_id,user);
                            /*SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE).edit();
                            editor.putBoolean("Favorite Removed",true);
                            editor.commit();*/
                            Toast toast2 = Toast.makeText(getApplicationContext() , "Removed", Toast.LENGTH_SHORT);
                            toast2.show();
                        }
                    }
                }
        );
    }
    public void saveFavorite(){
        db=new DbHelper(activity);
        favorite = new Movie();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");
        Toast toast = Toast.makeText(getApplicationContext() , user, Toast.LENGTH_SHORT);
        toast.show();
        int movie_id=getIntent().getExtras().getInt("movie_id");
        String rate=getIntent().getExtras().getString("vote_average");
        String pstr=getIntent().getExtras().getString("poster_path");
        //Toast.makeText(this, pstr, Toast.LENGTH_SHORT).show();
        String dte=getIntent().getExtras().getString("release_date");
        favorite.setId(movie_id);
        favorite.setOriginalTitle(title.getText().toString().trim());
        favorite.setPosterPath(pstr);
        favorite.setVoteAverage(rate);
        favorite.setOverview(description.getText().toString().trim());
        favorite.setReleaseDate(dte);
        db.addFavorite(favorite,user);
    }
}
