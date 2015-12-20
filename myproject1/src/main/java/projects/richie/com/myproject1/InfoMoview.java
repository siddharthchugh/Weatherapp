package projects.richie.com.myproject1;

import com.google.gson.annotations.Expose;

/**
 * Created by Richie on 22-10-2015.
 */
public class InfoMoview {

    @Expose
    public String detail_image;
   @Expose
    public String detail_title;

    @Expose
    private String title;

    @Expose
     private String release_date;

    @Expose
    private String backdrop_path;
    @Expose

    private String vote_average;

    @Expose

    private String overview;

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }



    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getDetail_image() {
        return detail_image;
    }

    public void setDetail_image(String detail_image) {
        this.detail_image = detail_image;
    }

    public String getDetail_title() {
        return detail_title;
    }

    public void setDetail_title(String detail_title) {
        this.detail_title = detail_title;
    }

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
    public String moviewName;
/*
    @Expose
    private List<Results> results= new ArrayList<Results>();

    public List<Results> getresults() {
        return results;
    }

    public void setresults(List<Results> rs) {
        this.results = rs;
    }
*/

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
