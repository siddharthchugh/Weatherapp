package projects.richie.com.myproject1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Observable;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    TextView forecsastData;
    private TextView mid;
    private ProgressBar bar;
    private int id;
    private List<MovieDetail> infom;

    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private static final String FORECAST_SHARE_HASHTA = " #PopMovie ";
    private String mForecast;
    private final String URL_MOVIE_LINK = "http://api.themoviedb.org";
    final String URL = "http://image.tmdb.org/t/p/w500/";
    private TextView title;
   private  ImageView backgroundImage;
    private TextView releaseDate;
    private TextView voteAverage;
    private TextView overView;
    private Intent in;
    private ProgressBar progrssBar;

   //private List<MovieDetailInfo> movieInfo;

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
        title= (TextView) v.findViewById(R.id.detailTitle);
        backgroundImage= (ImageView) v.findViewById(R.id.detail_bgImage);
        releaseDate = (TextView) v.findViewById(R.id.releaseData);
        voteAverage = (TextView) v.findViewById(R.id.voteData);
        overView = (TextView) v.findViewById(R.id.synopsisContent);
        progrssBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progrssBar.setVisibility(View.VISIBLE);
        mid = (TextView) v.findViewById(R.id.movieID);
        in = getActivity().getIntent();


        mForecast = in.getStringExtra("movieid");
        ((TextView) v.findViewById(R.id.movieID))
                .setText(mForecast);
        Detail();

        return v;

    }


    public void Detail() {

  requestData();
    /*    if (isConnecetd()) {
         requestData("http://api.themoviedb.org/3/movie/"+ mForecast.toString()+"?api_key=8ab57b43e21f9bae201c7c686efee010");
     }
    */}
/*
    interface MovieInterface {

       */
/* @GET("/3/movie/{id}?api_key=8ab57b43e21f9bae201c7c686efee010")
        void getMovie(@Path("id")String id ,Callback<MovieDetail> cb);*//*

       @GET("/users/{username}")
       void getUser(@Path("username") String username, Callback<MovieDetail> cb);



    }
*/
    protected boolean isConnecetd() {

        ConnectivityManager manageOnline = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manageOnline.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {

            return true;
        } else {
            return false;
        }
    }

    private void requestData()  {

//       RestAdapter adapter = new RestAdapter.Builder().setEndpoint(URL_MOVIE_LINK).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_MOVIE_LINK).build();

        MovieInterface apiService =
                retrofit.create(MovieInterface.class);
       mForecast = in.getStringExtra("movieid");
       Call<MovieDetail> call = apiService.getUser(mForecast);
        call.enqueue(new Callback<MovieDetail>() {

            @Override
            public void onResponse(Response<MovieDetail> response, Retrofit retrofit) {
                int statusCode = response.code();
                MovieDetail user = response.body();

                progrssBar.setVisibility(View.INVISIBLE);

                title.setText(user.getTitle().toString());
                releaseDate.setText(user.getRelease_date().toString());
                voteAverage.setText(user.getVote_average().toString());
                overView.setText(user.getOverview().toString());


                Picasso.with(getContext())
                        .load(URL + user.getBackdrop_path())
                        .into(backgroundImage);


            }

            @Override
            public void onFailure(Throwable t) {
                String merror = t.getMessage();

            }
        });
/*
        in.getMovie(new Callback<MovieDetail>() {
            @Override
            public void success(MovieDetail infoMoview, Response response) {
                progrssBar.setVisibility(View.INVISIBLE);

                title.setText(infoMoview.getTitle().toString());
                releaseDate.setText(infoMoview.getRelease_date().toString());
                voteAverage.setText(infoMoview.getVote_average().toString());
                overView.setText(infoMoview.getOverview().toString());


                Picasso.with(getContext())
                        .load(URL + infoMoview.getBackdrop_path())
                        .into(backgroundImage);


            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();

            }
        });
*/
   }

    protected void updated() {


/*
             releaseDate.setText(it.getRelease_date());
                       voteAverage.setText(it.getVote_average());
                     overView.setText(it.getOverview());
*/

         //}
  //     }
           // releaseDate.setText(md.getRelease_date().toString());
            //          voteAverage.setText(md.getVote_average().toString());
            //        overView.setText(md.getOverview().toString());


            //      }
/*
        releaseDate.setText(infom.getRelease_date().toString());
        voteAverage.setText(inform.getVote_average().toString());
        overView.setText(infoMoview.getOverview().toString());
*//*
        }*/
   //     title.setText(ino.getTitle());

    }
/*
    public class MovieDetailInfo extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
updated();
            if (movieInfo.size() == 0) {

          //     bar.setVisibility(View.VISIBLE);
            }
            movieInfo.add(this);

        }

        @Override
        protected String doInBackground(String... params) {


            String content = HttpManger.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            infom = MoviesJson.imageConversion(s);

            updated();

            movieInfo.remove(this);
            if (movieInfo.size() == 0) {
           //    bar.setVisibility(View.INVISIBLE);

            }


        }

    }

*/



}



