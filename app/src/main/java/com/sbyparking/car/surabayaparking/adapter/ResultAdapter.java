package com.sbyparking.car.surabayaparking.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.ZoomDialog;
import com.sbyparking.car.surabayaparking.activity.ChatroomActivity;
import com.sbyparking.car.surabayaparking.activity.ResultActivity;
import com.sbyparking.car.surabayaparking.model.Parking;
import com.sbyparking.car.surabayaparking.util.LoginDialog;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private List<Parking> parkingList;

    Context context;

    ResultActivity resultActivity;
    FirebaseAuth mAuth;

    public ResultAdapter(List<Parking> parkingList, ResultActivity resultActivity) {
        this.parkingList = parkingList;
        this.resultActivity = resultActivity;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_result, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Parking parking = parkingList.get(position);

        holder.tvPencarianZona.setText(parking.pencarianZona);
        holder.tvSaranZona.setText(parking.saranZona);
        holder.tvWaktuTempuh.setText("Waktu Tempuh    : " + parking.waktuTempuh);
        holder.tvHargaMobil.setText("Mobil : Rp." + parking.hargaTiketMobil);
        holder.tvhargaMotor.setText("Motor : Rp." + parking.hargaTiketMotor);
        holder.tvJamOperasional.setText("Jam Operasional : " + parking.jamOperasional);
        holder.ivFoto.setImageResource(parking.photo);

        holder.btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri.Builder directionsBuilder = new Uri.Builder()
                        .scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("destination", parking.lat + "," + parking.lng);

                context.startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
            }
        });

        holder.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser == null) {
                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.show(((FragmentActivity)context).getSupportFragmentManager(), "login dialog");
                } else {
                    Intent chatroomIntent = new Intent(context, ChatroomActivity.class);
                    chatroomIntent.putExtra("parkingId", parking.id);
                    context.startActivity(chatroomIntent);
                }
            }
        });

        holder.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("saranZona", parking.saranZona);
                b.putInt("photo", parking.photo);

                ZoomDialog dialog = new ZoomDialog();
                dialog.setArguments(b);

                FragmentTransaction transaction = resultActivity.getSupportFragmentManager().beginTransaction();
                transaction.add(dialog, ZoomDialog.TAG);
                transaction.commitAllowingStateLoss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return parkingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivFoto;
        public TextView tvPencarianZona;
        public TextView tvSaranZona;
        public TextView tvWaktuTempuh;
        public TextView tvHargaMobil;
        public TextView tvhargaMotor;
        public TextView tvJamOperasional;
        public Button btnDirection;
        public Button btnChat;

        public ViewHolder(View view) {
            super(view);

            ivFoto = view.findViewById(R.id.ivFoto);
            tvPencarianZona = view.findViewById(R.id.tvPencarianZona);
            tvSaranZona = view.findViewById(R.id.tvSaranZona);
            tvWaktuTempuh = view.findViewById(R.id.tvWaktuTempuh);
            tvHargaMobil = view.findViewById(R.id.tvHargaMobil);
            tvhargaMotor = view.findViewById(R.id.tvHargaMotor);
            tvJamOperasional = view.findViewById(R.id.tvJamOperasional);

            btnDirection = view.findViewById(R.id.btnDirection);
            btnChat = view.findViewById(R.id.btnChat);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longItemClickListener != null) {
                        longItemClickListener.onLongItemClick(v, getLayoutPosition());
                    }
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnLongItemClickListener {
        void onLongItemClick(View itemView, int position);
    }

    private static OnItemClickListener itemClickListener;
    private static OnLongItemClickListener longItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnLongClickListener(OnLongItemClickListener listener) {
        this.longItemClickListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }
}
