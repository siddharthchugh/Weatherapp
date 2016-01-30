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
    private ImageView favorite_Movie;
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
    private int flag = 0;
    private List<MovieDetailInfo> movieInfo;
    private float rate;
    private String movie_Title = null;
    private String movie_Image = null;
    private String movie_Release = null;
    private String movie_Rate = null;
    private String movie_Overview = null;
    private String movie_tag = null;
    private Toolbar tb;

    private static final String MOVIE_SHARE_HASHTAG = " #Popular Movie ";
    private String mForecast;

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

        Detail();

        return v;

    }


    public void Detail() {

        try {
            if (isConnecting()) {
                if (in != null) {
                    movieID = in.getStringExtra("movieid");
                    requestData("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=API");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnecting() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
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


    private void requestData(String url) {

        MovieDetailInfo info = new MovieDetailInfo();
        in = getActivity().getIntent();

        movieID = in.getStringExtra("movieid");

        info.execute("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=API");


    }


    private Intent createShareForecastIntent() {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecast + MOVIE_SHARE_HASHTAG);


        return shareIntent;
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

            update(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//           infom = MoviesJson.imageConversion(s);

            update(s);

            movieInfo.remove(this);
            if (movieInfo.size() == 0) {
                progrssBar.setVisibility(View.INVISIBLE);

            }


        }

    }
}






