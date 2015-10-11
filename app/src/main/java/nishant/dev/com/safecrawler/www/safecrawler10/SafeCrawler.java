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
        Parse.initialize(this, "PARSE_APPLICATION_ID", "PARSE_CLIENT_KEY");
    }
}
