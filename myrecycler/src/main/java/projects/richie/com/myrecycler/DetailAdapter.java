//package projects.richie.com.myrecycler;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Richie on 22-10-2015.
// */
//public class DetailAdapter extends ArrayAdapter<InfoMoview> {
//   /// private static final String LOG_TAG = AndroidFlavorAdapter.class.getSimpleName();
//    private LinearLayout movieLayout;
//
//    Context con;
//    List<InfoMoview> info;
//    /**
//     * This is our own custom constructor (it doesn't mirror a superclass constructor).
//     * The context is used to inflate the layout file, and the List is the data we want
//     * to populate into the lists
//     *
//     * @param context        The current context. Used to inflate the layout file.
//     * @param androidFlavors A List of AndroidFlavor objects to display in a list
//     */
//    public DetailAdapter(Activity context,int resource, List<InfoMoview> androidFlavors) {
//        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
//        // the second argument is used when the ArrayAdapter is populating a single TextView.
//        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
//        // going to use this second argument, so it can be any value. Here, we used 0.
//        super(context, resource, androidFlavors);
//        this.con = context;
//        this.info = androidFlavors;
//
//    }
//
//    /**
//     * Provides a view for an AdapterView (ListView, GridView, etc.)
//     *
//     * @param position    The AdapterView position that is requesting a view
//     * @param convertView The recycled view to populate.
//     *                    (search online for "android view recycling" to learn more)
//     * @param parent The parent ViewGroup that is used for inflation.
//     * @return The View for the position in the AdapterView.
//     */
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
//        InfoMoview androidFlavor = getItem(position);
//
//        // Adapters recycle views to AdapterViews.
//        // If this is a new View object we're getting, then inflate the layout.
//        // If not, this view already has the layout inflated from a previous call to getView,
//        // and we modify the View widgets as usual.
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movieitems, parent, false);
//        }
//        InfoMoview in = info.get(position);
//
//         ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);
//      /*  Picasso.with(con)
//                .load(" http://image.tmdb.org/t/p/w154/"+in.getMovieImages())
//                .into(iconView);
//*/
////        Picasso.with(con).load("http://image.tmdb.org/t/p/w500/&api_key=8ab57b43e21f9bae201c7c686efee010").into(iconView);
//
//
//
//
//        TextView versionNameView = (TextView) convertView.findViewById(R.id.list_item_version_name);
//        versionNameView.setText(in.getMoviewName());
//
///*
//        TextView versionNumberView = (TextView) convertView.findViewById(R.id.list_item_versionnumber_textview);
//        versionNumberView.setText(in.getMovieID());
//
//*/
//
//        movieLayout = (LinearLayout) convertView.findViewById(R.id.itemlayout);
//        movieLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                con.startActivity(new Intent(getContext(), DetailActivity.class));
//
//            }
//        });
//
//
//
//
//        return convertView;
//    }
//}