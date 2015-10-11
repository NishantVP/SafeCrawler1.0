package nishant.dev.com.safecrawler.www.safecrawler10;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by nishant on 10/10/15.
 */
public class SafeCrawler extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, ParseKeys.ParseKey1, ParseKeys.ParseKey2);
        // Note: I have declared string constants in a class named ParseKeys.
        // This class is not shared on GitHub for Maintaining the privacy
        // Add your Parse ApplicationID and Client Key there
    }
}
