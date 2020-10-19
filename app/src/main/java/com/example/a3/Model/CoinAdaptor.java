package com.example.a3.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.a3.R;

/**
 * Star Adapter class helps in managing, interacting and displaying
 * the star grid in the UI by modifying the existing BaseAdapter
 * class according to the application's need
 */
public class CoinAdaptor extends BaseAdapter {

    /* CLASS DATA MEMBERS */
    private final Context inputContext;
    private final CoinManager coinManager;

    /* CLASS MEMBER FUNCTIONS */
    public CoinAdaptor(Context context) {
        this.inputContext = context;
        this.coinManager = CoinManager.getInstance();
    }

    @Override
    public int getCount() {
        return coinManager.getGrid().length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(inputContext);
        convertView = layoutInflater.inflate(R.layout.box_name, null, true);
        ImageView imageV = convertView.findViewById(R.id.grid_tresure);
        imageV.setMaxHeight((parent.getHeight() / coinManager.getCoin_height()) - 15);
        return convertView;
    }

}

