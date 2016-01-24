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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private TextView mid;
    private int id;
    private List<MovieDetail> infom;
  private ImageView  favorite_Movie;
    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private static final String MOVIE_SHARE_HASHTA = " #PopMovie ";
    private String movieID;
    final String URL = "http://image.tmdb.org/t/p/w500/";
    private TextView title;
    private ImageView backgroundImage;
    private TextView releaseDate;
    private TextView voteAverage;
    private TextView overView;
    private TextView tag;
    private Intent in;
    private RatingBar movierate;
    private ProgressBar progrssBar;
    private Float rt;
    private  int flag=0;
    private List<MovieDetailInfo> movieInfo;
    private float rate;
    private String movie_Title = null;
    private String movie_Image = null;
    private String movie_Release = null;
    private String movie_Rate = null;
    private String movie_Overview = null;
    private String movie_tag = null;
    private Toolbar tb;


    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        tb = (Toolbar) v.findViewById(R.id.toolbar);

        title = (TextView) v.findViewById(R.id.detailTitle);

        backgroundImage = (ImageView) v.findViewById(R.id.detail_bgImage);
        releaseDate = (TextView) v.findViewById(R.id.releaseData);
        voteAverage = (TextView) v.findViewById(R.id.voteData);
        overView = (TextView) v.findViewById(R.id.synopsisContent);
        tag = (TextView) v.findViewById(R.id.movietag);
        movierate = (RatingBar) v.findViewById(R.id.movie_ratingBar);
        progrssBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        progrssBar.setVisibility(View.INVISIBLE);
        in = getActivity().getIntent();
        movieInfo = new ArrayList<>();

       //favoriteAdded();
        Detail();

        return v;

    }


    public void Detail() {

        try {
            if (isConnecting()) {
                if (in != null) {
                    movieID = in.getStringExtra("movieid");
                    requestData("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=8ab57b43e21f9bae201c7c686efee010");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnecting() {
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    public void update(String msg) {
        JSONObject userObject = null;
        try {
            userObject = new JSONObject(msg);

            movie_Title = userObject.getString("original_title");
            movie_tag = userObject.getString("tagline");
            Picasso.with(getContext())
                    .load(URL + userObject.getString("backdrop_path"))
                    .into(backgroundImage);
            movie_Release = userObject.getString("release_date");
            movie_Rate = userObject.getString("vote_average");
            movie_Overview = userObject.getString("overview");
            title.setText(movie_Title);
            tag.setText(movie_tag);
            releaseDate.setText(movie_Release);
            voteAverage.setText(movie_Rate);
            rt = Float.parseFloat(movie_Rate);
            movierate.setRating(rt);
            overView.setText(movie_Overview);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onStart() {
        super.onStart();
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        Detail();

    }

    /*
    public void favoriteAdded( ){

        if(flag==1){

            flag=1;

 favorite_Movie.setImageResource(R.mipmap.ic_favorite_white);
        }
        else
        {
            flag=0;
            favorite_Movie.setImageResource(R.mipmap.ic_favorite_border);

        }


    }
*/



    private void requestData(String url) {

        MovieDetailInfo info = new MovieDetailInfo();
        in = getActivity().getIntent();

        movieID = in.getStringExtra("movieid");

        info.execute("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=8ab57b43e21f9bae201c7c686efee010");

/*
RestAdapter adapter = new RestAdapter.Builder().setEndpoint(URL_MOVIE_LINK).build();
            // Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_MOVIE_LINK).build();

            MovieInterface apiService =
                    adapter.create(MovieInterface.class);
            movieID = in.getStringExtra("movieid");

            apiService.getMovie(new Callback<MovieDetail>() {

                @Override
                public void success(final MovieDetail user, Response response) {

                    progrssBar.setVisibility(View.INVISIBLE);

                    title.setText(user.getTitle().toString());
                    releaseDate.setText(user.getRelease_date().toString());
                    voteAverage.setText(user.getVote_average());
                    overView.setText(user.getOverview().toString());

                    Picasso.with(getContext())
                            .load(URL + user.getBackdrop_path())
                            .into(backgroundImage);
                }


                @Override
                public void failure(RetrofitError error) {
                    String merror = error.getMessage();

                }

            });


*/


    }







    public class MovieDetailInfo extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (movieInfo.size() == 0) {

                progrssBar.setVisibility(View.VISIBLE);
            }
            movieInfo.add(this);

        }

        @Override
        protected String doInBackground(String... params) {



            String content = HttpManger.getData(params[0]);

try{
    Thread.sleep(100);

    update(params[0]);

}catch(Exception e){

    e.printStackTrace();

}

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            infom = MoviesJson.imageConversion(s);

            update(s);

            movieInfo.remove(this);
            if (movieInfo.size() == 0) {
                progrssBar.setVisibility(View.INVISIBLE);

            }


        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fav:
                if(flag==1){

                    flag=1;

                    favorite_Movie.setImageResource(R.mipmap.ic_favorite_white);
                }
                else
                {
                    flag=0;
                    favorite_Movie.setImageResource(R.mipmap.ic_favorite_border);

                }
                return false;
            default:
                break;
        }

        return false;
    }}






