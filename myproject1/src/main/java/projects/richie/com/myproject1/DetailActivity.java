package projects.richie.com.myproject1;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private final String URL_MOVIEDETAIL_LINK="http://api.themoviedb.org/3/movie/206647?api_key=8ab57b43e21f9bae201c7c686efee010";
   /// private final String URL_movie_details;
   // private List<MoviewGrid> detail;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 getSupportActionBar().setHomeButtonEnabled(true);




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


}
