
package projects.richie.com.myproject1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private View rootView;
    private GridView gridView;
    private ProgressBar bar;
    private final String URL_MOVIE_LINK="http://api.themoviedb.org/3/discover/movie?page=1&certification_coutnry=in&&api_key=8ab57b43e21f9bae201c7c686efee010";

    private final String URL_TOPRATEDMOVIE_LINK="http://api.themoviedb.org/3/movie/top_rated?api_key=8ab57b43e21f9bae201c7c686efee010";

    private final String URL_POPULARMOVIE_LINK="http://api.themoviedb.org/3/movie/popular?api_key=8ab57b43e21f9bae201c7c686efee010";

    private TextView movieData;
  private List<InfoMoview> moviedetails;
    private List<MoviewGrid> grid;
    private List<PopularMoviewGrid> grid1;

    public MovieDisplay() {
   setHasOptionsMenu(true);
    }


/*
    final InfoMoview[] androidFlavors = {
            new InfoMoview("Cupcake", "1.5", R.drawable.eclair),
            new InfoMoview("Donut", "1.6", R.drawable.donut),
            new InfoMoview("Eclair", "2.0-2.1", R.drawable.eclair),
            new InfoMoview("Froyo", "2.2-2.2.3", R.drawable.froyo),
            new InfoMoview("GingerBread", "2.3-2.3.7", R.drawable.gingerbread),
            new InfoMoview("Honeycomb", "3.0-3.2.6", R.drawable.honeycomb),
            new InfoMoview("Ice Cream Sandwich", "4.0-4.0.4", R.drawable.icecream),
            new InfoMoview("Jelly Bean", "4.1-4.3.1", R.drawable.jellybean),
            new InfoMoview("KitKat", "4.4-4.4.4", R.drawable.kitkat),
            new InfoMoview("Lollipop", "5.0-5.1.1", R.drawable.lollipop)
    };
*/



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//     flavorAdapter = new AndroidFlavorAdapter(getActivity(),(Arrays.asList(androidFlavors)));


movieAdapter=                 new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.movieitems, // The name of the layout ID.
                        R.id.list_item_version_name, // The ID of the textview to populate.
                        new ArrayList<String>());

// Now that we have some dummy forecast data, create an ArrayAdapter.
// The ArrayAdapter will take data from a source (like our dummy forecast) and
// use it to populate the ListView it's attached to.


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

       gridView = (GridView) rootView.findViewById(R.id.movieGrid);
        bar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);
        grid = new ArrayList<>();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(getContext(),"Clicked"+movieAdapter.getItem(i),Toast.LENGTH_SHORT).show();
            }
        });


         requestData(URL_MOVIE_LINK);

        return rootView;

    }


    protected void updated(){


        final AndroidFlavorAdapter aapt = new AndroidFlavorAdapter(getActivity(), R.layout.movieitems, moviedetails);
        gridView.setAdapter(aapt);

     }


    private void requestData(String url) {
        MoviewGrid mg = new MoviewGrid();
        mg.execute(url);
    }

/*
    private void requestPopularData(String url) {
        PopularMoviewGrid pmg = new PopularMoviewGrid();
        pmg.execute(url);
    }
*/

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


           String content =  HttpManger.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            moviedetails = MoviesJson.parseFeed(s);
            updated();

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



    public class PopularMoviewGrid extends AsyncTask<String,String,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

           if(grid1.size()==0) {

                bar.setVisibility(View.VISIBLE);
            }
            grid1.add(this);


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
            updated();

            grid1.remove(this);
            if(grid1.size()==0){
                bar.setVisibility(View.INVISIBLE);

            }


        }

        @Override
        protected void onProgressUpdate(String... values) {
            // updated(values[0]);
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
        switch (id){
            case R.id.action_popular:
                if(isConnecetd()) {
                    requestData(URL_POPULARMOVIE_LINK);

                }
                else
                {
                    Toast.makeText(getContext(), "Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.action_highestrated:
                if(isConnecetd()) {
                    requestData(URL_TOPRATEDMOVIE_LINK);

                }
                else
                {
                    Toast.makeText(getContext(),"Please connect to the network", Toast.LENGTH_SHORT).show();

                }


                break;

        }



        return super.onOptionsItemSelected(item);

    }
}



