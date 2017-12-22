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

public class login extends AppCompatActivity {
    TextView ltext;
    EditText lemail;
    EditText lpassword;
    Button lbutton;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String L_URL="https://capricious-soup.000webhostapp.com/registrationusers/volleylogin.php";
    String email,password;
    Boolean checkedittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ltext=(TextView)findViewById(R.id.ltext);
        lemail=(EditText)findViewById(R.id.lemail);
        lpassword=(EditText)findViewById(R.id.lpassword);
        lbutton=(Button)findViewById(R.id.lbutton);

        requestQueue= Volley.newRequestQueue(login.this);
        progressDialog=new ProgressDialog(login.this);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditText();
                if (checkedittext){
                    Userlogin();
                }else {
                    Toast.makeText(getApplicationContext(),"Fill all Fields",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void Userlogin() {
        progressDialog.setMessage("Please Wait!");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, L_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        if (response.equalsIgnoreCase("Data Matched")){
                            Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent=new Intent(login.this,home.class);
                            intent.putExtra("useremail",email);
                            startActivity(intent);

                        }else {
                            Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(login.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(login.this);
        requestQueue.add(stringRequest);

    }

    private void CheckEditText() {
        email=lemail.getText().toString().trim();
        password=lpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            checkedittext=false;
        }else {
            checkedittext=true;
        }

    }
}
