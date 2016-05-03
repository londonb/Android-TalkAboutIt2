package com.epicodus.talkaboutit2.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 5/2/16.
 */
@Parcel
public class Comment {
    String text;
    String post;

    public Comment() {}

    public Comment(String text, String post) {
        this.text = text;
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public String getText() {
        return text;
    }
}

