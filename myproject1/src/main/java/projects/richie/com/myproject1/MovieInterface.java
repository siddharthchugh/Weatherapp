package projects.richie.com.myproject1;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Richie on 19-12-2015.
 */
public   interface MovieInterface {

//        @GET("/3/movie/102899?api_key=8ab57b43e21f9bae201c7c686efee010")

        @GET("/3/movie/{id}?api_key=8ab57b43e21f9bae201c7c686efee010")
        void getMovie(@Path("id")String id ,Callback<MovieDetail> cb);

        @GET("/users/{username}")
        void getUser(@Path("username") String username, Callback<MovieDetail> cb);


}
