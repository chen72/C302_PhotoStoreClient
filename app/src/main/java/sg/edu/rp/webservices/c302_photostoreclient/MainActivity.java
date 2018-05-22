package sg.edu.rp.webservices.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<String> alCategories = new ArrayList<String>();
    ArrayAdapter<String> aaCategories;


    ListView lv;
    CategoryAdapter aa;
    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void onResume() {
        super.onResume();
//
//        lvCategories = (ListView) findViewById(R.id.listViewCategories);
//        aaCategories = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alCategories);
//        lvCategories.setAdapter(aaCategories);

        categories = new ArrayList<Category>();

        lv = (ListView)findViewById(R.id.listViewCategories) ;
        aa = new CategoryAdapter(this, R.layout.row, categories);
        lv.setAdapter(aa);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category s = categories.get(position);
                int cID = s.getId();
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                i.putExtra("id", cID);
                Log.i("info", cID+"");
                startActivity(i);
            }
        });



        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getCategories.php");
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

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");

                            categories.add(new Category(categoryId,categoryName,description));
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
