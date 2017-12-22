package com.hammer.volleysqlphp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Infolist extends AppCompatActivity {
List<Registrationmodel> registrationmodels;
RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
RecyclerView.Adapter adapter;
ProgressDialog progressDialog;
String DATA_URL="https://capricious-soup.000webhostapp.com/registrationusers/json.php";
String id;
String name;
String emailid;
String password;
Button infobutton;
JsonArrayRequest jsonArrayRequest;
RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infolist);
        //define array list
        registrationmodels=new ArrayList<>();
        //binding objects
        recyclerView=(RecyclerView)findViewById(R.id.infoview);
        infobutton=(Button)findViewById(R.id.infobutton);

        progressDialog=new ProgressDialog(Infolist.this);

        //all about recyclerview
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait!");
                progressDialog.show();

            jsondatawebcall();

            }
        });



    }

    private void jsondatawebcall() {
//same as in volley instead of stringrequest we write jsonArrayrequest and do not write post method in response
    jsonArrayRequest=new JsonArrayRequest(DATA_URL,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                jsondataafterwebcall(response);

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Infolist.this,error.toString(),Toast.LENGTH_LONG).show();
                }
            });
requestQueue= Volley.newRequestQueue(this);
requestQueue.add(jsonArrayRequest);
    }

    private void jsondataafterwebcall(JSONArray response) {

        for (int i=0;i<response.length();i++){
            Registrationmodel registrationmodel=new Registrationmodel();
            JSONObject json=null;


            try {
                    json=response.getJSONObject(i);
                registrationmodel.setId(json.getInt(id));
                registrationmodel.setName(json.getString(name));
                registrationmodel.setEmail_Id(json.getString(emailid));
                registrationmodel.setPassword(json.getString(password));
            }catch (JSONException e){
                e.printStackTrace();
               // Toast.makeText(Infolist.this,"Successful",Toast.LENGTH_LONG).show();
            }
        }
        adapter=new Rviewadapter(registrationmodels,this);
        recyclerView.setAdapter(adapter);
       }


}
