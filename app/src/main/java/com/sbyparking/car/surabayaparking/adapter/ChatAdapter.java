package com.sbyparking.car.surabayaparking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.activity.ViewImageActivity;
import com.sbyparking.car.surabayaparking.model.Chat;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by haimax on 25/12/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private List<Chat> chatList;
    FirebaseUser currentUser;
    Context mContext;
    Calendar calendar;

    public ChatAdapter(List<Chat> chatList, Context mContext) {
        this.chatList = chatList;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        this.mContext = mContext;
        calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == MSG_TYPE_LEFT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
        }

        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatAdapter.ViewHolder holder, int position) {
        final Chat chat = chatList.get(position);

        String prevDate = null;
        if (position > 1) {
            calendar.setTimeInMillis(Long.parseLong(chatList.get(position - 1).timestamp.toString()));
            prevDate = DateFormat.format("dd MMMM yyyy", calendar).toString();
        }

        calendar.setTimeInMillis(Long.parseLong(chat.timestamp.toString()));
        String time = DateFormat.format("HH:mm", calendar).toString();
        String date = DateFormat.format("dd MMMM yyyy", calendar).toString();

        holder.tvDisplayName.setText(chat.name);
        holder.tvImageTime.setText(time);
        holder.tvChatTime.setText(time);
        Glide.with(mContext).load(chat.photo).into(holder.ivProfileImage);

        if (prevDate == null || !date.equals(prevDate)) {
            Log.d("ChatAdapter", chat.message + " | pos: " + position);
            holder.tvChatDate.setText(date);
        } else {
            holder.tvChatDate.setVisibility(View.GONE);
        }

        if (chat.type.equals("image")) {
            Glide.with(mContext).load(chat.message).into(holder.ivImageMessage);
            holder.tvMessage.setVisibility(View.GONE);
            holder.tvChatTime.setVisibility(View.GONE);

            holder.ivImageMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ViewImageActivity.class);
                    intent.putExtra("url", chat.message);
                    mContext.startActivity(intent);
                }
            });
        } else {
            holder.tvMessage.setText(chat.message);
            holder.ivImageMessage.setVisibility(View.GONE);
            holder.tvImageTime.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView ivProfileImage;
        TextView tvDisplayName, tvMessage, tvChatTime, tvImageTime, tvChatDate;
        ImageView ivImageMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivChatProfileImage);
            tvDisplayName = itemView.findViewById(R.id.tvChatDisplayName);
            tvMessage = itemView.findViewById(R.id.tvChatMessage);
            ivImageMessage = itemView.findViewById(R.id.ivImageMessage);
            tvChatTime = itemView.findViewById(R.id.tvChatTime);
            tvImageTime = itemView.findViewById(R.id.tvImageTime);
            tvChatDate = itemView.findViewById(R.id.tvChatDate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).uid.equals(currentUser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
