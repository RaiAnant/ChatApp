package com.projects.rtk154.firebasedemoapp;

/**
 * Created by rtk154 on 29/6/18.
 */

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;



/**
 * Created by rtk154 on 28/6/18.
 */

public class CheckPermission {

    //  CHECK FOR LOCATION PERMISSION
    public static boolean checkPermission(Activity activity){
        int result = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    //REQUEST FOR PERMISSSION
    public static void requestPermission(Activity activity, final int code){

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)){

            Toast.makeText(activity,"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(activity,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},code);
        }
    }

}
