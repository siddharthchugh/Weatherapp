package projects.richie.com.myproject1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private final String URL_MOVIEDETAIL_LINK="http://api.themoviedb.org/3/movie/206647?api_key=8ab57b43e21f9bae201c7c686efee010";
   /// private final String URL_movie_details;
    private List<MoviewGrid> detail;
    private TextView titleMovie,releaseDate,synopsisData;
    private ProgressBar bar;
    private int id;
    private List<InfoMoview> info;
    private static final String FORECAST_SHARE_HASHTA = " #Sunshine ";
    private String movieID;
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       titleMovie  = (TextView) findViewById(R.id.title);
        bar= (ProgressBar) findViewById(R.id.progressBar);
        titleMovie.setMovementMethod(new ScrollingMovementMethod());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 getSupportActionBar().setHomeButtonEnabled(true);
       detail = new ArrayList<>();

       Detail();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       switch (id){
           case android.R.id.home:
         NavUtils.navigateUpFromSameTask(this);

       }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
      finish();

    }

    public void Detail(){

        if(isConnecetd()) {

            Intent in = getIntent();
            if (in != null && in.hasExtra(Intent.EXTRA_TEXT)) {


                String forecastStr = in.getStringExtra(Intent.EXTRA_TEXT);
                movieID = in.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) findViewById(R.id.title))
                        .setText(forecastStr);


              }
            requestData("http://api.themoviedb.org/3/movie/"+movieID+"?api_key=8ab57b43e21f9bae201c7c686efee010");

        }


    }





    protected boolean isConnecetd(){

        ConnectivityManager manageOnline = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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
             titleMovie.setText(tl.getMovieID()+" ");

        }


    }

    private void requestData(String url) {



        MoviewGrid mg = new MoviewGrid();
        mg.execute(url);

    }

    public class MoviewGrid extends AsyncTask<String,String,String> {


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
        protected String doInBackground(String... params) {

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

}
