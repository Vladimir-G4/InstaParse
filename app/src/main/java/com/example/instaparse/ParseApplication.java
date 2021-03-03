package com.example.instaparse;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("QeESvrf5KzZDiNCH28KWPmYOMT72X7t5tUuRm3uE")
                .clientKey("O8Z5bB09Bid2DFTMpVNd2agXgP1paFRaS6a9qYQu")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
