package com.epicodus.talkaboutit2.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 5/2/16.
 */
@Parcel
public class Comment {
    Post post;
    String text;

    public Comment() {}

    public Comment(String text, Post post) {
        this.text = text;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }
}

