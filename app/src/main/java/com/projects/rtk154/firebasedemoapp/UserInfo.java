package com.projects.rtk154.firebasedemoapp;

/**
 * Created by rtk154 on 27/6/18.
 */

public class UserInfo {
    public String mName,mAddress,mLocation;

    public UserInfo(){}
    public UserInfo(String Name, String Address,String Location) {
        this.mName = Name;
        this.mAddress = Address;
        this.mLocation=Location;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }
}
