package com.hammer.volleysqlphp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView text;
    EditText username;
    EditText useremail;
    EditText userpassword;
    Button submit;
    String name;
    String email;
    String pass;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    String R_URl="https://capricious-soup.000webhostapp.com/registrationusers/volleyregister.php";
   //String R_URl="https://capricious-soup.000webhostapp.com/registrationusers/volleyupdate.php";
    //String R_URl="https://capricious-soup.000webhostapp.com/registrationusers/volleydelete.php";
    Boolean checkedittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.text);
        username=(EditText)findViewById(R.id.username);
        useremail=(EditText)findViewById(R.id.useremail);
        userpassword=(EditText)findViewById(R.id.userpassword);
        submit=(Button)findViewById(R.id.submit);

        requestQueue= Volley.newRequestQueue(MainActivity.this);
        progressDialog=new ProgressDialog(MainActivity.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if (checkedittext){
                    UserRegistration();
                }else {
                    Toast.makeText(MainActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void UserRegistration() {
        progressDialog.setMessage("Please Wait!");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,R_URl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                       if (response.equalsIgnoreCase("Success")){
                           Toast.makeText(MainActivity.this,"User Registration Successfull", Toast.LENGTH_LONG).show();
                          //Toast.makeText(MainActivity.this,"updated successfully",Toast.LENGTH_LONG).show();
                           //Toast.makeText(MainActivity.this,"deleted successfully",Toast.LENGTH_LONG).show();
                           finish();
                           Intent intent=new Intent(getApplicationContext(),login.class);
                           startActivity(intent);
                       }else{
                           Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                       }
                        /* Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),login.class);
                        startActivity(intent);*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<String, String>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",pass);

                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);



    }

    private void CheckEditTextIsEmptyOrNot() {
        name=username.getText().toString().trim();
        email=useremail.getText().toString().trim();
        pass=userpassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
            checkedittext=false;
        }else {
            checkedittext=true;
        }

    }
}
