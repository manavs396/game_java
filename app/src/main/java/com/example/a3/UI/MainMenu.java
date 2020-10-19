// This is activity comes after welcom screen when you select for starting the game or can change the specs of game

package com.example.a3.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3.R;

public class MainMenu extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setHelpButton();
        setGameButton();
        setOptionsButton();
    }

    final void setGameButton() {

        final Button gameopt = findViewById(R.id.playgame);
        gameopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options = Game.makeIntent(MainMenu.this);
                startActivity(options);
            }
        });
    }

    final void setOptionsButton() {

        final Button gameopt = findViewById(R.id.optionbutton);
        gameopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options = OptionsMenu.makeIntent(MainMenu.this);
                startActivity(options);
            }
        });
    }

    final void setHelpButton() {

        final Button gameopt = findViewById(R.id.help_mainmenu);
        gameopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options = Help.makeIntent(MainMenu.this);
                startActivity(options);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        return;
    }
}


