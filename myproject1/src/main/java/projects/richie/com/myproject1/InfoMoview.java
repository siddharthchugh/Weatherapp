package projects.richie.com.myproject1;

/**
 * Created by Richie on 22-10-2015.
 */
public class InfoMoview {


    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
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
    public int movieID;
    public String moviewName;
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
