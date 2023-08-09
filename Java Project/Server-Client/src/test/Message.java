package test;

import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable{
    private String text;
    private boolean ex;

    public Message(String txt) {
        this.text = txt;
        if (txt.equals("exit")){
            ex=!ex;
        }
    }
    public boolean getEx(){
        return ex;
    }

    public String getText() {
        return text;
    }
}