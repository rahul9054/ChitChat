package com.labawsrh.aws.rvitemanimaion;

public class Message {
    String Sender, messageText, date;
    int userPhoto;

    public Message() {
    }

    public Message(String sender, String messageText, String date, int userPhoto) {
        Sender = sender;
        this.messageText = messageText;
        this.date = date;
        this.userPhoto = userPhoto;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }
}
