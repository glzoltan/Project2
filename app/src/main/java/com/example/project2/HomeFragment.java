package com.example.project2;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<Movie> movieList;
    public static final String LOG_TAG=MoviesAdapter.class.getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        //initViews();
        recyclerView=v.findViewById(R.id.recyclermovies);
        movieList = new ArrayList<>();
        /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
        adapter = new MoviesAdapter(this.getActivity(),movieList);
        /*if(getActivity().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        }*/
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        loadJSON();
        return v;
    }
    private void initViews(){

    }
    private void loadJSON(){
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getActivity(),"API Key ERROR",Toast.LENGTH_SHORT).show();
                return;
            }
            Client Client = new Client();
            Service apiService=Client.getClient().create(Service.class);
            Call<MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    List<Movie> movies =response.body().getResults();
                    recyclerView.setAdapter(new MoviesAdapter(getActivity().getApplicationContext(),movies));
                    recyclerView.smoothScrollToPosition(0);

                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Toast.makeText(getActivity(),"ERROR Fetching data",Toast.LENGTH_SHORT).show();

                }
            });

        }catch(Exception e){
            Toast.makeText(getActivity(),"ERROR 3",Toast.LENGTH_SHORT).show();
        }
    }

}
