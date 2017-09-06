package com.appit.AnimationsAndBottomSheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by appit on 7/4/17.
 */

public class ChatModel {

    private String created_by;
    private String image_url;
    private String wrapper;
    private String text;
    private long created_on, modified_on;
    private int type = -1;
    private boolean isGroup = false;
    private String message_id;

    public ChatModel() {
    }

    public ChatModel(String uid, String photo_original_url, String wrapper, String text,
                     int type, long created_on, long modified_on) {
        created_by = uid;
        image_url = photo_original_url;
        this.wrapper = wrapper;
        this.text = text;
        this.created_on = created_on;
        this.modified_on = modified_on;
        this.type = type;

    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }



    /*public Map getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Map timeStamp) {
        this.timeStamp = timeStamp;
    }*/

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreated_on() {
        return created_on;
    }

    public void setCreated_on(long created_on) {
        this.created_on = created_on;
    }

    public long getModified_on() {
        return modified_on;
    }

    public void setModified_on(long modified_on) {
        this.modified_on = modified_on;
    }


    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("created_by", created_by);
        result.put("image_url", image_url);
        result.put("text", text);
        result.put("wrapper", wrapper);
        result.put("created_on", created_on);
        result.put("modified_on", modified_on);
        result.put("type", type);
        result.put("isGroup", isGroup);
        return result;
    }
}
