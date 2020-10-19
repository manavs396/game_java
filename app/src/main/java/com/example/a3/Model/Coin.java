package com.example.a3.Model;

public class Coin {

    boolean isCoin;
    boolean isVisible;
    boolean isBonus;
    boolean Found;
    boolean CoinCheck;

    public Coin(){
        isCoin = false;
        isVisible = false;
        isBonus = false;
        Found = false;
        CoinCheck = false;
    }

    public boolean getisCoin(){
        return isCoin;
    }
    public boolean getisVisible(){
        return isVisible;
    }
    public boolean getisBonus(){
        return isBonus;
    }
    public boolean getFound(){
        return Found;
    }
    public boolean CoinCheck(){
        return CoinCheck;
    }
    public void setisCoin(boolean inp_coin){
        this.isCoin = inp_coin;
    }
    public void setisVisible(boolean inp_isVisible){
        this.isVisible = inp_isVisible;
    }
    public void setisBonus(boolean inp_isBonus){
        this.isBonus = inp_isBonus;
    }
    public void setFound(boolean inp_found){
        this.Found = inp_found;
    }
    public void setCoinCheck(boolean inp_coincheck){
        this.CoinCheck = inp_coincheck;
    }
    public void reset(){
        this.isCoin = false;
        this.isVisible = false;
        this.isBonus = false;
        this.Found = false;
        this.CoinCheck = false;
    }
}
