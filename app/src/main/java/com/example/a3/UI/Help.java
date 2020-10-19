// This activity shows the discription of game with citation of images that is used for theme
package com.example.a3.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3.R;

public class Help extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        return new Intent(context, Help.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initializeHyperlinks();

    }

    private void initializeHyperlinks() {
        TextView developername = findViewById(R.id.designers);
        Spanned developerText = Html.fromHtml("<a href='https://opencoursehub.cs.sfu.ca/jackt/grav-cms/cmpt276/home'>Designed By: Manav Sharma & Payal payal</a>");
        developername.setMovementMethod(LinkMovementMethod.getInstance());
        developername.setText(developerText);

        TextView citations = findViewById(R.id.citationsText);
        Spanned citationText = Html.fromHtml("<a href=https://www.pngwave.com/png-clip-art-oidsu>Closed Treasure Box Image</a>" + " ,   "
                + "<a href=https://homenetgames.com/games/1/screenshots/2017/07/360/the-pirate-screenshot_2.jpg>Background Image</a>" + " ,   "
                + "<a href=https://i.pinimg.com/originals/c8/88/4d/c8884d6baa63c7497d28a9fae4f87a03.png>Coin Image</a>");
        citations.setMovementMethod(LinkMovementMethod.getInstance());
        citations.setText(citationText);

        return;
    }


}
