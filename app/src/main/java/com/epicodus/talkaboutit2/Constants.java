package com.epicodus.talkaboutit2;

/**
 * Created by Guest on 5/2/16.
 */
public class Constants {
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String FIREBASE_LOCATION_SEARCHED_CATEGORY = "addedCategory";
    public static final String FIREBASE_POSTS_NODE = "posts";
    public static final String FIREBASE_URL_CATEGORIES = FIREBASE_URL + "/" + FIREBASE_LOCATION_SEARCHED_CATEGORY;
    public static final String FIREBASE_URL_POSTS = FIREBASE_URL + "/" + FIREBASE_POSTS_NODE;
}
