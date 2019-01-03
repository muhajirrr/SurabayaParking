package com.sbyparking.car.surabayaparking.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.adapter.ChatAdapter;
import com.sbyparking.car.surabayaparking.model.Chat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatroomActivity extends AppCompatActivity {

    private static final int ACTION_SELECT_IMAGE = 1;
    private static final int ACTION_CAPTURE_IMAGE = 2;

    private static final int REQUEST_IMAGE_SEND = 1;

    FirebaseUser user;
    DatabaseReference chatRef;

    StorageReference mStorageRef;

    int parkingId;
    private List<Chat> chatList;
    private ChatAdapter chatAdapter;

    @BindView(R.id.etMessage) EditText etMessage;
    @BindView(R.id.rvChatList) RecyclerView rvChatList;
    @BindView(R.id.pbChatRoom) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        ButterKnife.bind(this);

        parkingId = getIntent().getIntExtra("parkingId", 1);
        user = FirebaseAuth.getInstance().getCurrentUser();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        if (user == null) {
            goToLoginActivity();
        }

        chatRef = FirebaseDatabase.getInstance().getReference("chats").child(Integer.toString(parkingId));
        rvChatList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvChatList.setLayoutManager(linearLayoutManager);

        retrieveMessage();
    }

    private void sendMessage(String message) {
        String uid = user.getUid();
        String name = user.getDisplayName();
        String photo = user.getPhotoUrl().toString();

        DatabaseReference newChatRef = chatRef.push();

        Chat chat = new Chat(uid, name, photo, message, "text");
        newChatRef.setValue(chat);
    }

    private void sendImageMessage(Uri imagePath) {
        progressBar.setVisibility(View.VISIBLE);
        final DatabaseReference newChatRef = chatRef.push();
        String key = newChatRef.getKey();

        final StorageReference storageImageRef = mStorageRef.child(Integer.toString(parkingId) + "/" + key +".jpg");

        UploadTask uploadTask = storageImageRef.putFile(imagePath);

        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return storageImageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(ChatroomActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    String uid = user.getUid();
                    String name = user.getDisplayName();
                    String photo = user.getPhotoUrl().toString();

                    String downloadUrl = task.getResult().toString();
                    Chat chat = new Chat(uid, name, photo, downloadUrl, "image");
                    newChatRef.setValue(chat);
                }

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void goToLoginActivity() {
        Intent loginIntent = new Intent(ChatroomActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public void goToSendImageActivity(int action) {
        Intent sendImageIntent = new Intent(ChatroomActivity.this, SendImageActivity.class);
        sendImageIntent.putExtra("action", action);
        startActivityForResult(sendImageIntent, REQUEST_IMAGE_SEND);
    }

    private void retrieveMessage() {
        progressBar.setVisibility(View.VISIBLE);
        chatList = new ArrayList<>();

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    chatList.add(chat);
                }

                chatAdapter = new ChatAdapter(chatList, getApplicationContext());
                rvChatList.setAdapter(chatAdapter);
                rvChatList.smoothScrollToPosition(chatList.size());

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(ChatroomActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnSendMessage)
    public void send() {
        String message = etMessage.getText().toString();

        if (!message.isEmpty()) {
            sendMessage(message);
            etMessage.setText("");
        }
    }

    @OnClick(R.id.btnSelectPicture)
    public void selectPicture() {
        goToSendImageActivity(ACTION_SELECT_IMAGE);
    }

    @OnClick(R.id.btnTakePicture)
    public void takePicture() {
        goToSendImageActivity(ACTION_CAPTURE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_SEND && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri imagePath = data.getData();
            sendImageMessage(imagePath);
        }
    }
}
