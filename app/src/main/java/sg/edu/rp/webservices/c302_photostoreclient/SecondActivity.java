
package sg.edu.rp.webservices.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
//    private ListView lvCategories;
//    ArrayList<String> alCategories = new ArrayList<String>();
//    ArrayAdapter<String> aaCategories;


    ListView lv;
    DetailAdapter aa;
    ArrayList<Detail> details;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
         id = i.getIntExtra("id",0);

}

    protected void onResume() {
        super.onResume();
//

//        lvCategories = (ListView) findViewById(R.id.listViewCategories);
//        aaCategories = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alCategories);
//        lvCategories.setAdapter(aaCategories);
        Toast.makeText(this,id+"",Toast.LENGTH_SHORT);

        details = new ArrayList<Detail>();

        lv = (ListView)findViewById(R.id.lv) ;
        aa = new DetailAdapter(this, R.layout.detail_row, details);
        lv.setAdapter(aa);
        Log.i("info", id+"");

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id="+id);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end
    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int photo_id = jsonObj.getInt("photo_id");
                            String title = jsonObj.getString("title");
                            Log.i("info", title);

                            String description = jsonObj.getString("description");
                            String image = jsonObj.getString("image");
                            int category_id = jsonObj.getInt("category_id");
                            String created_by = jsonObj.getString("created_by");

                            details.add(new Detail(photo_id,title,description,image,category_id,created_by));
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aa.notifyDataSetChanged();
                }
            };
    // Code for step 2 end

}
