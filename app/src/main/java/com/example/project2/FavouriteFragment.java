package com.example.project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavouriteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<Movie> movieList;
    private DbHelper db;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String LOG_TAG=MoviesAdapter.class.getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourite,container,false);

        recyclerView=v.findViewById(R.id.recyclermovies);
        movieList = new ArrayList<>();
        /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
        adapter = new MoviesAdapter(this.getActivity(),movieList);
        //recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        db=new DbHelper(getActivity());
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");
        getAllFavorite(user);
        return v;
    }
    private void getAllFavorite(final String user){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params){
                movieList.clear();
                movieList.addAll(db.getAllFavorite(user));
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
