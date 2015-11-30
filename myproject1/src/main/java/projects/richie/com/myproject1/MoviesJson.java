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



        try {
            JSONObject ob = new JSONObject(content);
            JSONArray ar = ob.getJSONArray("results");//new JSONArray(content);
            List<InfoMoview> movieList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

               JSONObject obj = ar.getJSONObject(i);
                InfoMoview list = new InfoMoview();

                list.setMovieID(obj.getInt("id"));

                list.setMoviewName(obj.getString("poster_path"));
                list.setMoviewName(obj.getString("title"));

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
          // JSONArray ar = ob.getJSONArray("results");//new JSONArray(content);
            List<InfoMoview> movieList = new ArrayList<>();
          //  for (int i = 0; i < ob.length(); i++) {
              //  JSONObject obj = ob.getJSONObject(i);
                InfoMoview list = new InfoMoview();
//               list.setMovieID(ob.getInt("id"));
                list.setMoviewName(ob.getString("title"));
          //  list.setDetail(ob.getString("overview"));



/*
                list.setMoviewName(obj.getString("poster_path"));
                list.setMoviewName(obj.getString("title"));
*/



                movieList.add(list);


            //}
            return movieList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

}
