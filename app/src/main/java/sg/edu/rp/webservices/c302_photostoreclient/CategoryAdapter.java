package sg.edu.rp.webservices.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter {

    private ArrayList<Category> categories;
    private Context context;
    private TextView tvName,tvDesc;

    public CategoryAdapter(Context context, int resource, ArrayList<Category> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        categories = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvName = (TextView) rowView.findViewById(R.id.tvName);
        // Get the ImageView object
        tvDesc = (TextView) rowView.findViewById(R.id.tvDescription);


        // The parameter "position" is the index of the
        //        //  row ListView is requesting.
        //  We get back the food at the same index.
        Category currentCategory = categories.get(position);
        // Set the TextView to show the food

        tvName.setText(currentCategory.getName());
        tvDesc.setText(currentCategory.getDescription());


        // Return the nicely done up View to the ListView
        return rowView;
    }

}
