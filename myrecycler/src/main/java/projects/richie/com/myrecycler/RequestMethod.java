package projects.richie.com.myrecycler;

/**
 * Created by Richie on 17-05-2015.
 */

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Richie on 28-04-2015.
 */
public class RequestMethod {

    private String uri;
    private String method= "GET";
    private Map<String,String> params = new HashMap<>();

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }


    public void setParams(String key,String value){

        params.put(key,value);
    }

    public String getEncodedParams(){
        StringBuilder sb = new StringBuilder();
        for(String key : params.keySet()){
            String values;
            values = URLEncoder.encode(params.get(key));
            if(sb.length()>0){
                sb.append("&");
            }
            sb.append(key + ":" +values);

        }
        return sb.toString();
    }
}
