package projects.richie.com.myproject1;

import android.content.Intent;
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

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    TextView forecsastData;
//    private List<MovieDetail> detail;
    private ProgressBar bar;
    private int id;
    private List<InfoMoview> info;

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

    public static DetailActivityFragment newInstance(int index) {
        DetailActivityFragment f = new DetailActivityFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("movieid", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("movieid", 0);
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

/*
        in = getActivity().getIntent();


             mForecast = in.getStringExtra("movieid");
              ((TextView) v.findViewById(R.id.title))
                      .setText(mForecast);

*/

        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    Detail();
    }


    public void Detail() {

            requestData();

    }

    public void identity(){

        getShownIndex();
    }


    public interface MovieApi {

        @GET("/3/movie/206647?api_key=API")

        void getMovie(Callback<InfoMoview> cb);
    }

    private void requestData()  {

            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(URL_MOVIE_LINK).build();
            MovieApi api = adapter.create(MovieApi.class);
            api.getMovie(new Callback<InfoMoview>() {

                @Override
                public void success(InfoMoview infoMoview, Response response) {
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


   }
}



