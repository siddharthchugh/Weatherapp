package projects.richie.com.myproject1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richie on 22-10-2015.
 */
public class InfoMoview implements Parcelable{



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

    public String movieID;
    public String moviewName;

    public String movieImages;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public InfoMoview(Parcel in){
        movieImages = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(movieImages);
    }

    public final Parcelable.Creator<InfoMoview> CREATOR = new Parcelable.Creator<InfoMoview>() {
        @Override
        public InfoMoview createFromParcel(Parcel parcel) {
            return new InfoMoview(parcel);
        }

        @Override
        public InfoMoview[] newArray(int i) {
            return new InfoMoview[i];
        }

    };


/*
   public InfoMoview(String m_name,String id,int img){

       this.movieID= id;
       this.moviewName= m_name;
       this.movieImages= img;
   }
*/


}
