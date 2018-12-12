package com.sbyparking.car.surabayaparking.util;

/*
 * Created by Meidika on 06/02/2018.
 */

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/* Created by Meidika on 7/31/2015.
 */
public class CustomDialog extends DialogFragment {

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager.findFragmentByTag(tag) == null) {
            try {
                super.show(manager, tag);
            }catch(IllegalStateException e){
                Toast.makeText(getActivity()
                        , "Mohon maaf terjadi kesalahan"
                        ,Toast.LENGTH_LONG).show();
            }
        }
    }
}