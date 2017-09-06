package com.appit.AnimationsAndBottomSheet;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by appit on 7/6/17.
 */

public class Wrapper implements Parcelable {
    String inAppProductID = "";
    String id = "";
    String price = "";
    String description = "";
    String title = "";
    String iconName = "";
    ArrayList<String> wrappers = new ArrayList<>();

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getInAppProductID() {
        return inAppProductID;
    }

    public void setInAppProductID(String inAppProductID) {
        this.inAppProductID = inAppProductID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getWrappers() {
        return wrappers;
    }

    public void setWrappers(ArrayList<String> wrappers) {
        this.wrappers = wrappers;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.inAppProductID);
        dest.writeString(this.id);
        dest.writeString(this.price);
        dest.writeString(this.description);
        dest.writeString(this.title);
        dest.writeString(this.iconName);
        dest.writeStringList(this.wrappers);
    }

    public Wrapper() {
    }

    protected Wrapper(Parcel in) {
        this.inAppProductID = in.readString();
        this.id = in.readString();
        this.price = in.readString();
        this.description = in.readString();
        this.title = in.readString();
        this.iconName = in.readString();
        this.wrappers = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Wrapper> CREATOR = new Parcelable.Creator<Wrapper>() {
        @Override
        public Wrapper createFromParcel(Parcel source) {
            return new Wrapper(source);
        }

        @Override
        public Wrapper[] newArray(int size) {
            return new Wrapper[size];
        }
    };
}
