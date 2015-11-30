package projects.richie.com.myproject1;

/**
 * Created by Richie on 17-05-2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Richie on 28-04-2015.
 */
public class HttpManger {

    public static String getData(String url){

        BufferedReader reader=null;
/*
       String uri = p.getUri();
if(p.getMethod().equals("GET")){
   uri+="?"+p.getEncodedParams();
}
*/
        try{

            URL uri = new URL(url);
           // Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
           HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

//           JSONObject json = new JSONObject(p.getParams());
//           String params  = "userID="+ json.toString();


/*
  if(p.getMethod().equals("POST")){

      con.setDoOutput(true);
      OutputStreamWriter write = new OutputStreamWriter(con.getOutputStream());
      write.write(p.getEncodedParams());
     // write.write(params);
      write.flush();
  }
*/
            StringBuilder build = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = reader.readLine())!=null){
                build.append(line+"\n");
            }

            return build.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

/*
    public static String getData(String url,String username,String pwd){

        BufferedReader reader=null;
        byte[] loginByte = (username+ ":" +pwd).getBytes() ;
        StringBuilder loginbuild = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginByte, Base64.DEFAULT));

*/
/*
       String uri = p.getUri();
if(p.getMethod().equals("GET")){
   uri+="?"+p.getEncodedParams();
}
*//*

        try{
            URL uri = new URL(url);
            HttpURLConnection con = (HttpURLConnection) uri.openConnection();
            con.addRequestProperty("Authorization",loginbuild.toString());
//
//           JSONObject json = new JSONObject(p.getParams());
//           String params  = "userID="+ json.toString();


*/
/*
  if(p.getMethod().equals("POST")){
           StringBuilder build = new StringBuilder().append("Basic ")
                   .append(Base64.encode(loginByte,Base64.DEFAULT));

      con.setDoOutput(true);
      OutputStreamWriter write = new OutputStreamWriter(con.getOutputStream());
      write.write(p.getEncodedParams());
     // write.write(params);
      write.flush();
  }
*//*

            StringBuilder buil = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;

            while((line = reader.readLine())!=null){
                buil.append(line+"\n");
            }

            return buil.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }
*/

}


