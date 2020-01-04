package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView title,description, rating,date;
    ImageView imageView;
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
        String thumbnail=getIntent().getExtras().getString("poster_path");
        String desc=getIntent().getExtras().getString("overview");
        String rat=String.valueOf(getIntent().getExtras().getString("vote_average"));
        String dte=getIntent().getExtras().getString("release_date");
        Glide.with(this).load(thumbnail).placeholder(R.drawable.ic_local_movies_black_24dp).into(imageView);
        title.setText(tit);
        description.setText(desc);
        rating.setText(rat);
        date.setText(dte);
    }
}
