package projects.richie.com.myproject1;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Richie on 06-12-2015.
 */
public interface MovieApi {


    @GET("/3/discover/movie?api_key=8ab57b43e21f9bae201c7c686efee010")

    void getMovie(Callback<InfoMoview>cb);
}
