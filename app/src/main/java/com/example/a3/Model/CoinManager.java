package com.example.a3.Model;

import java.util.Random;

public class CoinManager {

    private Coin[] array_bonus;
    private Coin[] array_coin;
    private int Coin_count;
    private int coin_height;
    private int coin_width;


    public static CoinManager instance;

    public CoinManager(int inp_countstars, int inp_coinheight, int inp_coinwidth) {
        if (inp_countstars < 0) {
            throw new IllegalArgumentException(" InValid!! Counting Coin!! ");
        } else {
            this.Coin_count = inp_countstars;
        }

        if (inp_coinheight <= 0) {
            throw new IllegalArgumentException("InValid!! Height of Coin");
        } else {
            this.coin_height = inp_coinheight;
        }

        if (inp_coinwidth <= 0) {
            throw new IllegalArgumentException("InValid!! Width of Coin");
        } else {
            this.coin_width = inp_coinwidth;
        }
        game_grid();
        this.setCoinRandom();
    }
    public static CoinManager getInstance(){
        if(instance == null) {
            instance = new CoinManager(0, 4, 6);
        }
        return instance;
        }
        public int getCoin_count() {
            return Coin_count;
        }
        public int getCoin_height(){
            return coin_height;
        }
        public int getCoin_width(){
            return coin_width;
        }
        public Coin[] getArray_bonus(){
            return array_bonus;
        }

    public void setCoin_count(int inp_countstars) {
        if (inp_countstars < 0) {
            throw new IllegalArgumentException(" InValid!! Counting Coin!! ");
        } else {
            this.Coin_count = inp_countstars;
        }
    }
    public void setCoin_height(int inp_coinheight){
        if (inp_coinheight <= 0) {
            throw new IllegalArgumentException("InValid!! Height of Coin");
        } else {
            this.coin_height = inp_coinheight;
        }
    }
    public void setCoin_width(int inp_coinwidth){
        if (inp_coinwidth <= 0) {
            throw new IllegalArgumentException("InValid!! Width of Coin");
        } else {
            this.coin_width = inp_coinwidth;
        }
    }
    public Coin[] getGrid() {
        return array_coin;
    }

    private void game_grid() {
        array_bonus = new Coin[coin_height*coin_width];
        array_coin = new Coin[this.coin_height * this.coin_width];

        for (int i = 0; i < this.array_coin.length; i++) {
            this.array_coin[i] = new Coin();
        }
    }

    public static CoinManager refreshBoardState(int inputNumberOfStars, int inputLengthOfStarField, int inputWidthOfStarField) {
        instance = new CoinManager(inputNumberOfStars, inputLengthOfStarField, inputWidthOfStarField);
        return instance;
    }


    public int getTile_grid_count(int Temp_index) {

        array_bonus = new Coin[Temp_index];

        if (Temp_index < 0) {
            throw new IllegalArgumentException("InValid!! Grid Index ");
        }
        if (array_coin[Temp_index].getisCoin() && ! array_coin[Temp_index].getFound()) {
            return -1;
        }
        int Mines_count = 0;
        int mines_bonus = 0;
        int divider = (Temp_index / coin_width);
        int Index_starting = (coin_width * divider);
        int Index_ending = (coin_width * (divider + 1));
        for (int i = Index_starting; i < Index_ending; i++) {
            if (array_coin[i].getisCoin() && !array_coin[i].getFound()) {
                Mines_count++;
            }
        }
        Index_starting = (Temp_index % coin_width);
        Index_ending = (coin_height * coin_width);
        for (int i = Index_starting; i < Index_ending; i += coin_width) {
            if (array_coin[i].getisCoin() && !array_coin[i].getFound()) {
                Mines_count++;
            }
        }
        return Mines_count;
    }

    private void setCoinRandom() {
        Random rand = new Random();
        for (int i = 0; i <Coin_count; ) {
            int randomIndex = rand.nextInt(this.getCoin_height() * this.getCoin_width());
            if (!this.array_coin[randomIndex].isCoin) {
                this.array_coin[randomIndex].isCoin = true;
                i++;
            }
        }
    }

    /* This function randomizes the grid during multiple gameplays */
    public void grid_random() {
        game_grid();
        setCoinRandom();
        return;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < (coin_height * coin_width); i++) {
            output.append(this.array_coin[i].isCoin);
            output.append(this.array_coin[i].isVisible);
            output.append(this.array_coin[i].Found);
            output.append('\t');
            if (((i + 1) % coin_width) == 0) {
                output.append('\n');
            }
        }
        return output.toString();
    }
}
