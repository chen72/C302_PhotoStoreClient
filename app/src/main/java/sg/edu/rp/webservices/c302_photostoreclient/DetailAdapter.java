package sg.edu.rp.webservices.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailAdapter extends ArrayAdapter {
    private ArrayList<Detail> details;
    private Context context;
    private TextView tvName,tvDesc;

    public DetailAdapter(Context context, int resource, ArrayList<Detail> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        details = objects;
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
        View rowView = inflater.inflate(R.layout.detail_row, parent, false);

        // Get the TextView object
        tvName = (TextView) rowView.findViewById(R.id.tvTitle);
        // Get the ImageView object
        tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);


        // The parameter "position" is the index of the
        //        //  row ListView is requesting.
        //  We get back the food at the same index.
        Detail currentDetail = details.get(position);
        // Set the TextView to show the food

        tvName.setText(currentDetail.getTitle());
        tvDesc.setText(currentDetail.getDescription()+"\nCreate By "+currentDetail.getCreated_by()+"\n"+currentDetail.getImage());


        // Return the nicely done up View to the ListView
        return rowView;
    }

}
