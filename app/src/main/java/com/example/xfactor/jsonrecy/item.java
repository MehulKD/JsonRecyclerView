package com.example.xfactor.jsonrecy;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarthak on 13-07-2015.
 */
public class item implements Parcelable {

    public String content;
    public String url;
    ArrayList<item> items;

    item(ArrayList<item> items)
    {
        this.items=items;
    }
    item(String content, String url) {
        this.content = content;
        this.url = url;

    }
    item(String content ) {
        this.content = content;
    }
    item(Context context)
    {

    }

    public String getContent() {
        return content;
    }


    protected item(Parcel in) {
        content = in.readString();
        url = in.readString();
    }

    public static final Creator<item> CREATOR = new Creator<item>() {
        @Override
        public item createFromParcel(Parcel in) {
            return new item(in);
        }

        @Override
        public item[] newArray(int size) {
            return new item[size];
        }
    };

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeString(url);
    }
}
