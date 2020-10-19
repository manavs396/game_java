/// THis is the activity where game get played where there will be number of scanned, counts and high score

package com.example.a3.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a3.Model.Coin;
import com.example.a3.Model.CoinAdaptor;
import com.example.a3.Model.CoinManager;
import com.example.a3.R;

/**
 * This Activity is for the game screen where game can played the activity where the actual game is played
 * Where you can the see the grid and number of scanned coins
 */
public class Game extends AppCompatActivity {

    int temp_bonus;
    CoinManager coinManager = CoinManager.getInstance();

    SharedPreferences temp_token;
    SharedPreferences option_game;
    SharedPreferences.Editor game_edit;

    int Scan_count = 0;
    int coin_count = 0;
    int score_count = 0;

    public static Intent makeIntent(Context context) {
        return new Intent(context, Game.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initializing Class DataMembers
        option_game = getApplicationContext().getSharedPreferences(getResources().getString(R.string.BlockName), 0);
        game_edit = option_game.edit();
        int count_playtime = 0;
        int playgame_time = option_game.getInt(getResources().getString(R.string.Gameplay), 0);
        playgame_time++;

        game_edit.putInt(getResources().getString(R.string.Gameplay), playgame_time);
        game_edit.apply();
        score_count++;

        if (playgame_time <= 1) {
            HighScore_again();
        }
        count_playtime++;

        TextView Play_game = findViewById(R.id.game_label_timeGame);
        Play_game.setText(getResources().getString(R.string.gameActivity_Time_GamePlay) + playgame_time);
        TextView feature_highScore = findViewById(R.id.gameActivity_label_highscore);

        int Index_select = option_game.getInt(getResources().getString(R.string.BoardSizes), 0);
        int coins_select = option_game.getInt(getResources().getString(R.string.CountCoins), 0);
        int gameIndex = (Index_select * 4) + coins_select;

        feature_highScore.setText(getResources().getString(R.string.gameActivity_high_Score) + option_game.getInt("game" + gameIndex, 0));

        //Reset the game grid
        String[] Height_board = getResources().getStringArray(R.array.game_Options_Heights);
        String[] Width_board = getResources().getStringArray(R.array.game_Options_Widths);
        String[] coins_amount = getResources().getStringArray(R.array.game_Options_countCoins);

        coinManager = CoinManager.refreshBoardState(Integer.parseInt(coins_amount[option_game.getInt(getResources().getString(R.string.CountCoins), 0)]),
                Integer.parseInt(Height_board[option_game.getInt(getResources().getString(R.string.BoardSizes), 0)]),
                Integer.parseInt(Width_board[option_game.getInt(getResources().getString(R.string.BoardSizes), 0)]));

        Game_Grid_start();

        //Initializing the views for game
        temp_bonus = 0;
        coinManager.grid_random();
        int temp_token3 = temp_bonus + 1;
        TextView pos_coin = findViewById(R.id.game_coinFound);
        pos_coin.setText(getResources().getString(R.string.gameActivity_PrefixCoin) + coin_count +
                getResources().getString(R.string.gameActivity_MiddleCoin) + coinManager.getCoin_count() + getResources().getString(R.string.gameActivity_SuffixCoin));

        TextView scansStatus = findViewById(R.id.gameActivity_label_scansUsed);
        temp_token3++;
        scansStatus.setText(getResources().getString(R.string.gameActivity_Scans_Used) + "0");
        return;
    }

    //-------------------------------------------------------------------------------------------------------
    //----------------------------------- Helper Function ---------------------------------------------------

    /* This function resets the high scores for all the existing game boards */
    protected void HighScore_again() {

        int board_count = getResources().getStringArray(R.array.GameBoardSizes).length;
        int count_coins1 = getResources().getStringArray(R.array.NumberOfCoins).length;
        for (int i = 0; i < (board_count * count_coins1); i++) {
            game_edit.putInt(getResources().getString(R.string.sharedgame) + i, 0);
        }
        game_edit.apply();
        return;
    }

    /* This function initializes the game grid and also sets the actions to be performed when an element in the grid is clicked */
    private void Game_Grid_start() {

        GridView grid_coin = findViewById(R.id.gameActivity_grid_stars);
        grid_coin.setHorizontalSpacing(15);
        grid_coin.setVerticalSpacing(15);

        grid_coin.setNumColumns(coinManager.getCoin_width());
        CoinAdaptor coinAdapter = new CoinAdaptor(this);
        grid_coin.setAdapter(coinAdapter);

        grid_coin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Coin temp_current = coinManager.getGrid()[position];
                if (!temp_current.getisVisible()) {
                    if (temp_current.getisCoin() && !temp_current.getFound()) {
                        NewCoin(parent, view, temp_current);
                    } else if (temp_current.getisCoin() && temp_current.getFound()) {
                        prevous_coin(parent, view, position, temp_current);
                    } else {
                        empty_coins(parent, view, position, temp_current);
                    }
                }
            }
        });
        return;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void NewCoin(AdapterView<?> inputParent, View inputView, Coin inputCurrentElement) {

        coin_count++;
        temp_bonus++;

        TextView detect_coin = findViewById(R.id.game_coinFound);
        detect_coin.setText(getResources().getString(R.string.gameActivity_PrefixCoin) + coin_count +
                getResources().getString(R.string.gameActivity_MiddleCoin) + coinManager.getCoin_count() +
                getResources().getString(R.string.gameActivity_SuffixCoin));

        if (coin_count == coinManager.getCoin_count()) {

            int Index_select = option_game.getInt(getResources().getString(R.string.BoardSizes), 0);
            int coins_select = option_game.getInt(getResources().getString(R.string.CountCoins), 0);

            int index_game = (Index_select * 4) + coins_select;
            int MaximumScore = Integer.parseInt(getResources().getString(R.string.game_maximumscore));
            int Before_max = option_game.getInt(getResources().getString(R.string.sharedgame) + index_game, 0);

            int currentScore = (MaximumScore - Scan_count);
            if (currentScore > Before_max) {
                game_edit.putInt(getResources().getString(R.string.sharedgame) + index_game, currentScore);
                game_edit.apply();
            }

            Congratulations stat = new Congratulations();
            stat.showDialog(Game.this);
        }

        ImageView elementImageComponent = (ImageView) ((ViewGroup) inputView).getChildAt(0);
        elementImageComponent.setImageResource(R.mipmap.coin_1_foreground);
        inputCurrentElement.setFound(true);

        // Reseting the sorrounding views
        int time_animate = getResources().getInteger(android.R.integer.config_shortAnimTime);
        for (int index = 0; index < inputParent.getChildCount(); ++index) {
            View element = inputParent.getChildAt(index);
            if (coinManager.getGrid()[index].getisVisible()) {
                TextView elementTextChild = (TextView) ((ViewGroup) element).getChildAt(1);
                elementTextChild.setText(coinManager.getTile_grid_count(index) + "");
                elementTextChild.animate().alpha(1f).setDuration(time_animate).setListener(null);
            }
        }

        return;
    }

    private void prevous_coin(AdapterView<?> inputParent, View inputView, int inputPosition, Coin inputCurrentElement) {

        ImageView Component_image = (ImageView) ((ViewGroup) inputView).getChildAt(0);
        Component_image.setColorFilter(Color.argb(200, 255, 255, 255));

        TextView Component_text = (TextView) ((ViewGroup) inputView).getChildAt(1);
        Component_text.setText(coinManager.getTile_grid_count(inputPosition) + "");
        Component_text.setTextColor(getResources().getColor(android.R.color.white));
        Component_text.setVisibility(View.VISIBLE);

        inputCurrentElement.setisVisible(true);
        Scan_count++;
        temp_bonus++;
        TextView scansUsed = findViewById(R.id.gameActivity_label_scansUsed);
        scansUsed.setText(getResources().getString(R.string.gameActivity_Scans_Used) + Scan_count);

        return;
    }

    private void empty_coins(AdapterView<?> inputParent, View inputView, int inputPosition, Coin inputCurrentElement) {

        ImageView Component_image = (ImageView) ((ViewGroup) inputView).getChildAt(0);
        Component_image.setImageResource(android.R.color.transparent);

        TextView Component_text = (TextView) ((ViewGroup) inputView).getChildAt(1);
        Component_text.setText(coinManager.getTile_grid_count(inputPosition) + "");
        Component_text.setVisibility(View.VISIBLE);

        inputCurrentElement.setisVisible(true);
        Scan_count++;
        temp_bonus++;

        TextView scansUsed = findViewById(R.id.gameActivity_label_scansUsed);
        scansUsed.setText("Scans Used: " + Scan_count);

        return;
    }
}
