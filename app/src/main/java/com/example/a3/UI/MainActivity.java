// This is the main activity the Welcome screen comes with logo and submission details and it shows the theme of game

package com.example.a3.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3.R;

public class MainActivity extends AppCompatActivity {

    private boolean temp_menu = false;
    private static int SPLASH_SCREEN = 4000;

    Animation topAnimation, bottomAnimation;
    TextView gameName, author1, author2;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        image = findViewById(R.id.gameicon);
        gameName = findViewById(R.id.textView);
        author1 = findViewById(R.id.textView2);
        author2 = findViewById(R.id.textView4);
        image.setAnimation(topAnimation);

        gameName.setAnimation(bottomAnimation);
        author1.setAnimation(bottomAnimation);
        author2.setAnimation(bottomAnimation);
        mainMenu();
        setSkipButton();

    }

    final void mainMenu() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!temp_menu) {
                    Intent mainMenu = MainMenu.makeIntent(MainActivity.this);
                    startActivity(mainMenu);
                }
            }
        }, SPLASH_SCREEN);
    }

    final void setSkipButton() {
        final Button skipButton = findViewById(R.id.skipScreen);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = MainMenu.makeIntent(MainActivity.this);
                temp_menu = true;
                startActivity(mainMenu);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        return;
    }


}
