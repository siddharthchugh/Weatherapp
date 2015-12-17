package projects.richie.com.myproject1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richie on 01-11-2015.
 */
public class MoviesJson {


    public static List<InfoMoview> parseFeed(String content) {

         InfoMoview list;

        try {
            JSONObject ob = new JSONObject(content);

            JSONArray ar = ob.getJSONArray("results");//new JSONArray(content);
            List<InfoMoview> movieList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                list = new InfoMoview();

                JSONObject obj = ar.getJSONObject(i);
                list.setMoviewName(obj.getString("title"));
               list.setMovieImages(obj.getString("poster_path"));
                list.setMovieID(obj.getString("id"));

                movieList.add(list);

            }
            return movieList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }


    public static List<InfoMoview> imageConversion(String content) {



        try {
             JSONObject ob = new JSONObject(content);
            List<InfoMoview> movieList = new ArrayList<>();
                InfoMoview list = new InfoMoview();
                 list.setDetail_title(ob.getString("title"));
               list.setDetail_image(ob.getString("backdrop_path"));

            movieList.add(list);


            //}
            return movieList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

}
