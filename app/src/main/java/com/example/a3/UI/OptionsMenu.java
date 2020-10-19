/* THis activity selects the magnitude of grid and number of coins and save them for the game screen*/

package com.example.a3.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3.Model.CoinManager;
import com.example.a3.R;

public class OptionsMenu extends AppCompatActivity {
    CoinManager coinManager = CoinManager.getInstance();
    SharedPreferences option_store;
    SharedPreferences.Editor editor;

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionsMenu.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionmenu);
        option_store = getApplicationContext().getSharedPreferences(getResources().getString(R.string.BlockName), 0);

        editor = option_store.edit();
        int count_spinner = 0;
        Spinner grid = findViewById(R.id.boardnum);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.GameBoardSizes, R.layout.spinner);

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dim);
        grid.setAdapter(spinnerAdapter);
        Spinner count_spin_coin = findViewById(R.id.coinum);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.NumberOfCoins, R.layout.spinner);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dim);
        count_spin_coin.setAdapter(spinnerAdapter);

        count_spinner++;
        grid.setSelection(option_store.getInt(getResources().getString(R.string.BoardSizes), 0));
        count_spin_coin.setSelection(option_store.getInt(getResources().getString(R.string.CountCoins), 0));
        final Button for_save = findViewById(R.id.savebtn);

        // For save button
        for_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count_spinner1 = 0;
                Spinner boardSizes = findViewById(R.id.boardnum);
                count_spinner1++;
                Spinner numOfCoins = findViewById(R.id.coinum);
                int selectedBoardSizeIndex = boardSizes.getSelectedItemPosition();
                int selectedNumberOfCoinIndex = numOfCoins.getSelectedItemPosition();

                help_for_save(selectedBoardSizeIndex, selectedNumberOfCoinIndex);

                String[] Height_bo = getResources().getStringArray(R.array.GameBoardHeights);
                String[] Width_bo = getResources().getStringArray(R.array.GameBoardWidths);
                String[] CoinsCount = getResources().getStringArray(R.array.NumberOfCoins);

                CoinManager.refreshBoardState(Integer.parseInt(CoinsCount[selectedNumberOfCoinIndex]), Integer.parseInt(Height_bo[selectedBoardSizeIndex]), Integer.parseInt(Width_bo[selectedBoardSizeIndex]));

                finish();
            }
        });

        final Button Button_for_reset = findViewById(R.id.reversehanges);
        Button_for_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt(getResources().getString(R.string.Gameplay), 0);
                int numOfBoards = getResources().getStringArray(R.array.GameBoardSizes).length;
                int numberOfCoins = getResources().getStringArray(R.array.NumberOfCoins).length;
                for (int i = 0; i < (numOfBoards * numberOfCoins); i++) {
                    editor.putInt(getResources().getString(R.string.sharedgame) + i, 0);
                }
                editor.apply();
                finish();
            }
        });
    }
//----------------------Helper Function----------------------------------------------------------

    private void help_for_save(int i, int countcoins) {

        int temp_count = 0;
        SharedPreferences.Editor editor = option_store.edit();
        editor.putInt(getResources().getString(R.string.BoardSizes), i);
        temp_count++;
        editor.putInt(getResources().getString(R.string.CountCoins), countcoins);
        editor.apply();
        return;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        return;
    }
}


