
package projects.richie.com.myproject1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import projects.richie.com.myproject1.ManageAdapter.AndroidFlavorAdapter;


/**
 * A placeholder fragment containing a simple view.
 */

public class MovieDisplay extends Fragment  {

    private ListView forecastList;

    int position = 0;
    private TextView vCount;
    int count;
    private View rootView;
    private GridView gridView;
    private static final int SPAN_COUNT = 2;

    private ProgressBar bar;
    private final String URL_TOPRATEDMOVIE_LINK = "http://api.themoviedb.org/3/movie/top_rated?api_key=API";

    private final String URL_POPULARMOVIE_LINK = "http://api.themoviedb.org/3/movie/popular?api_key=API";
    private final String STATE_MOVIES = "movie_list";
    private TextView movieData;
    public List<InfoMoview> moviedetails;
    private List<MoviewGrid> grid;
    AndroidFlavorAdapter aapt;
    private ArrayList<InfoMoview> movieList = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    boolean mDualPane;
    private Spinner choose;
    int mCurCheckPosition = 0;

    public MovieDisplay() {

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) rootView.findViewById(R.id.movieGrid);

        bar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);
        grid = new ArrayList<>();
        if (savedInstanceState == null) {
            Display();
        }

        return rootView;

    }


    private String formatMoivieSelection(String url) {

        SharedPreferences pref_Movie_Selected = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String movieType = pref_Movie_Selected.getString(
                getString(R.string.movie_key_value),
                getString(R.string.highest));
        Log.v("The type ", "Sorted :" + movieType);

        if (movieType.equals(getString(R.string.popular))) {

            requestData(URL_POPULARMOVIE_LINK);
        } else if (!movieType.equals(R.string.highest)) {

            requestData(URL_TOPRATEDMOVIE_LINK);

        }


        return movieType;
    }

    protected void updated() {

        aapt = new AndroidFlavorAdapter(getActivity(), moviedetails);
        gridView.setAdapter(aapt);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InfoMoview im = (InfoMoview) adapterView.getItemAtPosition(i);
                if (im != null) {
                    String weatherItem = im.getMovieID();
                    Intent d_Intent = new Intent(getActivity(), DetailActivity.class);
                    d_Intent.putExtra("movieid", weatherItem);
                    startActivity(d_Intent);

                }
            }
        });

    }




    public void Display() {

        if (isConnection()) {
            requestData(URL_POPULARMOVIE_LINK);

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        formatMoivieSelection(URL_TOPRATEDMOVIE_LINK);
    }

    private void requestData(String url) {

        MoviewGrid mg = new MoviewGrid();
        mg.execute(url);

    }


    protected boolean isConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;


    }


    public class MoviewGrid extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (grid.size() == 0) {

                bar.setVisibility(View.VISIBLE);
            }
            grid.add(this);


        }

        @Override
        protected String doInBackground(String... params) {


            String content = HttpManger.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            grid.remove(this);
            if (grid.size() == 0) {
                bar.setVisibility(View.INVISIBLE);

            }

            if(s!=null) {
              moviedetails = MoviesJson.parseFeed(s);
                updated();
            }
            else {
                Toast.makeText(getContext(), "Please connect to Intenet ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.action_sort:
                startActivity(new Intent(getActivity(), SettingActivity.class));



                break;

            case R.id.action_popular:
                if (isConnection()) {
                    requestData(URL_POPULARMOVIE_LINK);

                } else {
                    Toast.makeText(getContext(), "Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.action_highestrated:
                if (isConnection()) {
                    requestData(URL_TOPRATEDMOVIE_LINK);

                } else {
                    Toast.makeText(getContext(), "Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;


        }


        return super.onOptionsItemSelected(item);

    }
}


