package projects.richie.com.myproject1.ManageAdapter;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import projects.richie.com.myproject1.InfoMoview;
import projects.richie.com.myproject1.R;

public class AndroidFlavorAdapter extends ArrayAdapter<InfoMoview> {
    private static final String LOG_TAG = AndroidFlavorAdapter.class.getSimpleName();
 private TextView name,id_movie;
    Context con;
    private int id;
    InfoMoview in;
    final String URL = "http://image.tmdb.org/t/p/w780";
    List<InfoMoview> info;

/*     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
*/



    public AndroidFlavorAdapter(Activity context, List<InfoMoview> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    this.con = context;
        this.info = androidFlavors;

    }

    static public class MovieHolder{

        MovieHolder(View v){
            ImageView iv = (ImageView) v.findViewById(R.id.list_item_icon);
       }

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        //final InfoMoview androidFlavor = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        View v = convertView;

        MovieHolder mHolder = null;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.movieitems, parent, false);
          v.setTag(convertView);

            mHolder = new MovieHolder(v);
              }

        else
        {

           mHolder = (MovieHolder) v.getTag();
            Log.v("convertview recyceled",""+v);

        }
        in = info.get(position);

        ImageView iconView = (ImageView)v.findViewById(R.id.list_item_icon);
        Picasso.with(con)
                .load(URL+in.getMovieImages())
                .into(iconView);


       return v;
    }
}

