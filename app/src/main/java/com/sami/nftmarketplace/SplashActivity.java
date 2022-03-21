package com.sami.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // thread that redirects to the ListActivity

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(6100);
                    Intent intent = new Intent(SplashActivity.this, RecyclerActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    // Destructing the splashActivty

    protected void onPause() {
        super.onPause();
        this.finish();
    }


}