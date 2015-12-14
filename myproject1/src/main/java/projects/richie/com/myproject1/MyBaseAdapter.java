 package projects.richie.com.myproject1;

 import android.app.Activity;
 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.ImageView;
 import android.widget.TextView;

 import java.util.List;


/*
class Items {
    String data;
    int img;

    public Items(String data, int img) {
        this.data = data;
        this.img = img;
    }
}
*/


 public class MyBaseAdapter extends ArrayAdapter<Flower> {

     private Context con;
     private List<Flower> ls;

     TextView name;
     ImageView iv;
     MyBaseAdapter(Context c, int resources, List<Flower> fl) {
     super(c,resources,fl);
         this.con = c;
          this.ls=  fl;
     }


     @Override
     public View getView(int i, View v, ViewGroup parent) {


             LayoutInflater infl = (LayoutInflater) con
                     .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
             v = infl.inflate(R.layout.list_item, parent, false);

         Flower temp = ls.get(i);

             name = (TextView) v.findViewById(R.id.data);
             name.setText(temp.getName());

       /*  iv = (ImageView) v.findViewById(R.id.imageView);
         iv.setImageBitmap(temp.getBitmap());
*/

         return v;
         }



 }

