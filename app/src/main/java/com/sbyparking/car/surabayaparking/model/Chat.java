package com.sbyparking.car.surabayaparking.model;

import com.google.firebase.database.ServerValue;

/**
 * Created by haimax on 25/12/18.
 */

public class Chat {
    public String uid;
    public String name;
    public String photo;
    public String type;
    public String message;
    public Object timestamp;

    public Chat() {

    }

    public Chat(String uid, String name, String photo, String message, String type) {
        this.uid = uid;
        this.name = name;
        this.photo = photo;
        this.message = message;
        this.type = type;
        this.timestamp = ServerValue.TIMESTAMP;
    }
}
