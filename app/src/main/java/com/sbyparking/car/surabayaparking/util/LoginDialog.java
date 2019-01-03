package com.sbyparking.car.surabayaparking.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.sbyparking.car.surabayaparking.activity.ChatroomActivity;
import com.sbyparking.car.surabayaparking.R;

/**
 * Created by haimax on 13/12/18.
 */

public class LoginDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);

        builder.setView(view);

        view.findViewById(R.id.btnLoginDialogCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        view.findViewById(R.id.btnLoginDialogLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatroomIntent = new Intent(getActivity(), ChatroomActivity.class);
                startActivity(chatroomIntent);
                dismiss();
            }
        });

        return builder.create();
    }
}
