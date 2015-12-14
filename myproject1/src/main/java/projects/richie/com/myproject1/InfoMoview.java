package projects.richie.com.myproject1;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richie on 22-10-2015.
 */
public class InfoMoview {

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int page;
    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getMoviewName() {
        return moviewName;
    }

    public void setMoviewName(String moviewName) {
        this.moviewName = moviewName;
    }

    public String getMovieImages() {
        return movieImages;
    }

    public void setMovieImages(String movieImages) {
        this.movieImages = movieImages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;
    public String movieID;
    @Expose
    public String moviewName;
    @Expose
    private List<Results> results= new ArrayList<Results>();

    public List<Results> getresults() {
        return results;
    }

    public void setresults(List<Results> rs) {
        this.results = rs;
    }

    public String movieImages;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


/*
   public InfoMoview(String m_name,String id,int img){

       this.movieID= id;
       this.moviewName= m_name;
       this.movieImages= img;
   }
*/


}
