package com.labawsrh.aws.rvitemanimaion;

public class MessageItem {

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    String sender ;

    public MessageItem() {
    }

    String message;

    public MessageItem(String senders_name, String message, String type, long timestamp, boolean seen) {
        this.sender = senders_name;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
        this.seen = seen;
    }

    String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    long  timestamp ;
    boolean seen ;

}
