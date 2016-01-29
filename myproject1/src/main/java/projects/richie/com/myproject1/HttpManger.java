package projects.richie.com.myproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpManger {

    public static String getData(String url) {

        BufferedReader reader = null;
        try {

            URL uri = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            StringBuilder build = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                build.append(line + "\n");
            }

            return build.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

}

