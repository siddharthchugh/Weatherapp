
package projects.richie.com.myproject1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */

public class MovieDisplay extends Fragment {

    private ListView forecastList;
    ArrayAdapter<String> movieAdapter;
    private AndroidFlavorAdapter flavorAdapter;
    int position = 0;
    private TextView vCount;
    int count;
    private View rootView;
    private GridView gridView;
    private ProgressBar bar;
    private final String URL_MOVIE_LINK = "http://api.themoviedb.org/3/discover/movie?api_key=8ab57b43e21f9bae201c7c686efee010";

    private final String URL_TOPRATEDMOVIE_LINK = "http://api.themoviedb.org/3/movie/top_rated?api_key=8ab57b43e21f9bae201c7c686efee010";

    private final String URL_POPULARMOVIE_LINK = "http://api.themoviedb.org/3/movie/popular?api_key=8ab57b43e21f9bae201c7c686efee010";

    private TextView movieData;
    public List<InfoMoview> moviedetails;
    private List<MoviewGrid> grid;
    AndroidFlavorAdapter aapt;
    private Toolbar tb;
    boolean mDualPane;
    int mCurCheckPosition = 0;

    public MovieDisplay() {

        setHasOptionsMenu(true);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tb = (Toolbar) rootView.findViewById(R.id.toolbar);
        gridView = (GridView) rootView.findViewById(R.id.movieGrid);

        bar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);
        grid = new ArrayList<>();
        Display();

        return rootView;

    }



    protected void updated() {

        aapt = new AndroidFlavorAdapter(getActivity(),moviedetails);
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

    //                Toast.makeText(getContext(), weatherItem, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void Display() {

        if (isConnecetd()) {
            requestData(URL_MOVIE_LINK);

        }
    }


    private void requestData(String url) {

        MoviewGrid mg = new MoviewGrid();
        mg.execute(url);

    }


    protected boolean isConnecetd() {

        ConnectivityManager manageOnline = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manageOnline.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {

            return true;
        } else {
            return false;
        }
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

            moviedetails = MoviesJson.parseFeed(s);
            updated();

            grid.remove(this);
            if (grid.size() == 0) {
                bar.setVisibility(View.INVISIBLE);

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


            case R.id.action_popular:
                if (isConnecetd()) {
                    requestData(URL_POPULARMOVIE_LINK);

                } else {
                    Toast.makeText(getContext(), "Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.action_highestrated:
                if (isConnecetd()) {
                    requestData(URL_TOPRATEDMOVIE_LINK);

                } else {
                    Toast.makeText(getContext(), "Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

        }


        return super.onOptionsItemSelected(item);

    }
}



