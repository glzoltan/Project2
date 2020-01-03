package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mContext, List<Movie> movieList){
        this.mContext=mContext;
        this.movieList=movieList;
    }
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card, viewGroup,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(movieList.get(i).getOriginalTitle());
        String vote = String.valueOf(movieList.get(i).getVoteAverage());
        //viewHolder.userrating.setText();
        Glide.with(mContext).load(movieList.get(i).getPosterPath()).placeholder(R.drawable.ic_home_black_24dp).into(viewHolder.thumbnail);
    }
    @Override
    public int getItemCount(){
        return movieList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, userrating;
        public ImageView thumbnail;
        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.titleView);
           // userrating=(TextView)view.findViewById(R.id.ratingview);
            thumbnail = (ImageView)view.findViewById(R.id.thumbnail);
            /*view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos !=RecyclerView.NO_POSITION){
                        Movie clickedDataItem=movieList.get(pos);
                        FragmentTransaction frag_trans = getFragmentManager().beginTransaction();
                        frag_trans.replace(R.id.fragment_container,new SignupFragment());
                        frag_trans.commit();
                    }
                }
            });*/
        }
    }
}