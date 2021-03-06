package com.epicodus.talkaboutit2.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 5/2/16.
 */
@Parcel
public class Post {
    String title;
    String message;
    int voteCount = 0;
    String category;
    List<Comment> comments = new ArrayList<>();

    public Post() {}

    public Post(String title, String message, String category) {
        this.title = title;
        this.message = message;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getCategory() {
        return category;
    }

}
