package com.example.basicunitconvertersir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;import android.widget.Button;
import android.widget.TextView;import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;import com.google.android.gms.tasks.Task;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;


public class home extends AppCompatActivity implements View.OnClickListener
{

    ImageButton btnOpenMap;
    TextView tvOpenMap;
    ImageButton btnOpenNews;
    TextView tvOpenNews;
    ImageButton btnOpenAbout;
    TextView tvOpenAbout;
    GoogleSignInClient mGoogleSignInClient;
    String name, email;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //display

        TextView tvName = (TextView) findViewById(R.id.tvname);
        TextView tvEmail = (TextView) findViewById(R.id.tvemail);
        name = getIntent().getStringExtra("Name");
        email = getIntent().getStringExtra("Email");
        tvName.setText(name);
        tvEmail.setText(email);
        Button signout = findViewById(R.id.homepage);
        signout.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //get reference from edit text
        btnOpenMap= (ImageButton) findViewById(R.id.btnOpenMap);
        btnOpenNews=(ImageButton) findViewById(R.id.btnOpenNews);
        btnOpenAbout=(ImageButton) findViewById(R.id.btnOpenAbout);

        //connect to another page
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getApplicationContext(),MapsActivity.class);
                startActivity(intent1);
            }
        });
        btnOpenNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setClass(getApplicationContext(),NewsActivity.class);
                startActivity(intent2);
            }
        });


        btnOpenAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent();
                intent3.setClass(getApplicationContext(),AboutActivity.class);
                startActivity(intent3);
            }
        });




    }

    //START MENU
    //Public for Menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    //Public for handling menu item
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutus:
                Toast.makeText(this,"This is About Us Page",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this,AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.homepage:
                Toast.makeText(this,"This is Home Page",Toast.LENGTH_LONG).show();
               Intent intent1=new Intent(this,home.class);
               startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homepage:
               Toast.makeText(getApplicationContext(), email+" signed out.Thank you.",Toast.LENGTH_SHORT).show();
                signOut();
                break;
        }
    }
    //END MENU


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), email+" signed out.Thank you.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


}}