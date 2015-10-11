package nishant.dev.com.safecrawler.www.safecrawler10;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseObject;

public class LocationUpdateService extends Service {

    LocationManager locationManager;
    LocationListener locationListener;
    public LocationUpdateService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful

        Toast.makeText(this,"This is from service",Toast.LENGTH_LONG).show();

        // -- Save data to Parse from Service Testing -- //
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        RequestLocationInfo();
        return Service.START_NOT_STICKY;
    }

    public void RequestLocationInfo()
    {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        if(checkLocationPermission())
        {
            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        else
        {
            Toast.makeText(this,"Please Allow Location Services",Toast.LENGTH_LONG).show();
        }

        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if(checkLocationPermission())
                {
                    Log.d("New Location", "Stopping Location Updates");
                    // Stop getting updates of Location
                    locationManager.removeUpdates(locationListener);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Allow Location Services",Toast.LENGTH_LONG).show();
                }

            }
        }.start();

    }

    // This method checks permissions
    private boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    //After Getting new location this method is executed
    public void makeUseOfNewLocation(Location newLocation)
    {
        Double newLatitude;
        Double newLongitude;

        newLatitude = newLocation.getLatitude();
        newLongitude = newLocation.getLongitude();

        Log.d("New Location", newLatitude.toString() + " " +newLongitude.toString());

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
