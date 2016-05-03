package com.epicodus.talkaboutit2.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 5/2/16.
 */
@Parcel
public class Category {
    String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
