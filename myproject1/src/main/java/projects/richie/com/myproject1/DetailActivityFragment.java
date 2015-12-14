package projects.richie.com.myproject1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    TextView  forecsastData;
    private List<MoviewGrid> detail;
    private TextView titleMovie,releaseDate,synopsisData;
    private ProgressBar bar;
    private int id;
    private List<InfoMoview> info;

    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private static final String FORECAST_SHARE_HASHTA = " #PopMovie ";
    private String mForecast;
 private final String url = "http://api.themoviedb.org/3/movie/206647?api_key=8ab57b43e21f9bae201c7c686efee010";


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

        Intent in = getActivity().getIntent();

View v = inflater.inflate(R.layout.fragment_detail, container, false);

          if(in!=null&&in.hasExtra(Intent.EXTRA_TEXT)){

             mForecast = in.getStringExtra(Intent.EXTRA_TEXT);
/*
              ((TextView) v.findViewById(R.id.title))
                      .setText(forecastStr);
*/
Toast.makeText(getActivity(),"Clicked"+mForecast,Toast.LENGTH_LONG).show();

        }

/*
        if (in != null && in.hasExtra(Intent.EXTRA_TEXT)) {
            String forecastStr = in.getStringExtra(Intent.EXTRA_TEXT);
            mForecast = in.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) v.findViewById(R.id.title))
                    .setText(forecastStr);

        }
*/
//Detail();


        return v;

    }

    /*private Intent createShareForecastIntent(){

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mForecast+FORECAST_SHARE_HASHTA);


        return shareIntent;
    }
*/


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
  /*      if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }
*/

    }
    public void Detail(){

        if(isConnecetd()){

/*
            Bundle bd = getActivity().getIntent().getExtras();
            String msg = bd.getString("movieid");
                      int data = Integer.valueOf(msg);
*/
            requestData(url);


               requestData("http://api.themoviedb.org/3/movie/" + 206647 + "?api_key=8ab57b43e21f9bae201c7c686efee010");
            }

   else
           {
               Toast.makeText(getActivity(),"Not Applicaable.", Toast.LENGTH_SHORT).show();
           }

 }





    protected boolean isConnecetd(){

        ConnectivityManager manageOnline = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manageOnline.getActiveNetworkInfo();

        if(info != null && info.isConnected()){

            return  true;
        }
        else {
            return false;
        }
    }

    protected void updated() {

        if(info!=null){
            for(InfoMoview tl : info )
                titleMovie.append(tl.getId()+" "+tl.moviewName);

      /*      titleMovie.append(tl.getMoviewName()+" ");
*/
        }


    }

    private void requestData(String url) {

    /*    MoviewGrid mg = new MoviewGrid();
        mg.execute();
   */
    }

    public class MoviewGrid extends AsyncTask<RequestMethod,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            updated();
            if(detail.size()==0) {

                bar.setVisibility(View.VISIBLE);
            }
            detail.add(this);


        }

        @Override
        protected String doInBackground(RequestMethod... params) {

/*
            for(int i =0;i<params.length;i++){

                publishProgress("Movies Detail"+ params[i]+"\n");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
*/
            // String content =  HttpManger.getData(params[0]);

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            info = MoviesJson.imageConversion(s);
            updated();

            detail.remove(this);
            if(detail.size()==0){
                bar.setVisibility(View.INVISIBLE);

            }


        }

        @Override
        protected void onProgressUpdate(String... values) {
            // updated(values[0]);
        }
    }


/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }


    }
*/
}
