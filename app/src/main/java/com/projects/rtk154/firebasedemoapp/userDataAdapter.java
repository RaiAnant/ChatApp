package com.projects.rtk154.firebasedemoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rtk154 on 28/6/18.
 */

public class userDataAdapter extends ArrayAdapter<UserInfo> {

    ArrayList<UserInfo> userdata=new ArrayList<>();

    public userDataAdapter(@NonNull Context context, @NonNull ArrayList<UserInfo> objects) {
        super(context, 0, objects);
        userdata=objects;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

          View  listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_customlayout, null, true);
        TextView nameList=(TextView)listItemView.findViewById(R.id.nameListTV);
        TextView AddressList=(TextView)listItemView.findViewById(R.id.AdressListTV);
        TextView locationList=(TextView)listItemView.findViewById(R.id.LocationListTV);
        UserInfo userData = userdata.get(position);
        nameList.setText(userData.getmName().toString());
        AddressList.setText(userData.getmAddress().toString());
        String s=userData.getmLocation();
        locationList.setText(userData.getmLocation());
        return listItemView;
    }
}
