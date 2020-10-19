// This activity where game ends it shows the message , thanks
package com.example.a3.UI;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.a3.R;

public class Congratulations {

    public void showDialog(final Activity activity) {

        int count_coin = 0;
        final Dialog temp_dialog = new Dialog(activity);
        temp_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        temp_dialog.setCancelable(false);
        temp_dialog.setCanceledOnTouchOutside(false);
        temp_dialog.setContentView(R.layout.congratulations_layout);
        temp_dialog.show();

        Button ok = temp_dialog.findViewById(R.id.dialogBox_button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_dialog.dismiss();
                activity.finish();
            }
        });

        return;
    }

}
