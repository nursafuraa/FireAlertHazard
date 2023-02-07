package com.example.basicunitconvertersir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity {

    TextView textView,textView2textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11;
    TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        linkTextView = findViewById(R.id.textView11);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

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
}