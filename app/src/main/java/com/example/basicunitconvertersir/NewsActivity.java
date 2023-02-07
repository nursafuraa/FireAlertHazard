package com.example.basicunitconvertersir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public class NewsActivity extends AppCompatActivity {

    class News{
        String image,description,reporter,date;
    }

    class Response{
        boolean success;
        String message;
        News[] news;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        LinearLayout news = findViewById(R.id.news);

        Executors.newSingleThreadExecutor().execute(()->{
            try {
                HttpURLConnection conn= (HttpURLConnection) new URL("http://172.20.10.4/fire/news.php").openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();
                InputStream is;
                if(conn.getResponseCode()!=200){
                    is=conn.getErrorStream();
                }
                else
                    is=conn.getInputStream();
                StringBuilder sb=new StringBuilder();
                BufferedReader br= new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line=br.readLine())!=null){
                    sb.append(line);
                }
                Response response=new Gson().fromJson(sb.toString(), Response.class);
                if(response.success){
                    for(News n:response.news){
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(new URL(n.image).openStream());
                            runOnUiThread(()->{

                                View view = getLayoutInflater().inflate(R.layout.template_news,null);
                                ((ImageView)view.findViewById(R.id.imgNews)).setImageBitmap(bitmap);
                                ((TextView)view.findViewById(R.id.description)).setText(n.description);
                                ((TextView)view.findViewById(R.id.reporter)).setText(n.reporter);
                                ((TextView)view.findViewById(R.id.date)).setText(n.date);
                                news.addView(view);
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    runOnUiThread(()->{
                        Toast.makeText(this,response.message,Toast.LENGTH_SHORT).show();
                    });
                }
            }catch (Exception e){
                Log.e("error",e.getMessage(),e);
            }

        });
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