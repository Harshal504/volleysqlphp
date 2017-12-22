package com.hammer.volleysqlphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
TextView htext,hltext;
Button hlbutton,lvbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        htext=(TextView)findViewById(R.id.htext);
        hltext=(TextView)findViewById(R.id.hltext);
        hlbutton=(Button)findViewById(R.id.hlbutton);
        lvbutton=(Button)findViewById(R.id.lvbutton);
        String id=getIntent().getStringExtra("useremail");
        hltext.setText(hltext.getText()+id);

        hlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,"Logout successfull",Toast.LENGTH_LONG).show();
                finish();
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

        lvbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Infolist.class);
                startActivity(i);
            }
        });
    }
}
