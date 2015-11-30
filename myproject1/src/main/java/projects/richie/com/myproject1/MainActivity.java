package projects.richie.com.myproject1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private GridView gridView;
    private ProgressBar bar;
    private int id;
   private final String URL_MOVIE_LINK="http://api.themoviedb.org/3/discover/movie?page=1&certification_coutnry=in&&api_key=8ab57b43e21f9bae201c7c686efee010";

    private final String URL_TOPRATEDMOVIE_LINK="http://api.themoviedb.org/3/movie/top_rated?api_key=8ab57b43e21f9bae201c7c686efee010";
    private final String URL_POPULARMOVIE_LINK="http://api.themoviedb.org/3/movie/popular?api_key=8ab57b43e21f9bae201c7c686efee010";
    ArrayAdapter adapt;
    private TextView movieData;
    private Context con;
    private LinearLayout movieLayout;

    private List<InfoMoview> moviedetails;
    private List<MoviewGrid> grid;
    private InfoMoview mw;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*    bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);
*/  //      grid = new ArrayList<>();

//    requestData(URL_MOVIE_LINK);



    }




/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

*/


/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_popular:
                requestData(URL_POPULARMOVIE_LINK);



 if(isConnecetd()) {
                   requestData(URL_POPULARMOVIE_LINK);

                }
                else
                {
                    Toast.makeText(this, "Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.action_highestrated:
                if(isConnecetd()) {
                    requestData(URL_TOPRATEDMOVIE_LINK);

                }
                else
                {
                    Toast.makeText(this,"Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

        }




        return super.onOptionsItemSelected(item);
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


         adapt = new ArrayAdapter<String>(this, R.layout.movieitems,R.id.list_item_version_name);

        gridView = (GridView) findViewById(R.id.movieGrid);



        gridView.setAdapter(adapt);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                String  msg = adapt.getItem(i);

                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
   }

    private void requestData(String url) {
        MoviewGrid mg = new MoviewGrid();
        mg.execute(url);
    }
*/

    public class MoviewGrid extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(grid.size()==0) {

                bar.setVisibility(View.VISIBLE);
            }
            grid.add(this);


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


            String content =  HttpManger.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

           moviedetails = MoviesJson.parseFeed(s);
            //updated();

            grid.remove(this);
            if(grid.size()==0){
                bar.setVisibility(View.INVISIBLE);

            }


        }

        @Override
        protected void onProgressUpdate(String... values) {
            // updated(values[0]);
        }
    }




}
