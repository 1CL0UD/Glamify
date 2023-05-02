package com.project.glamify;

import java.util.ArrayList;

public class ProfileMenu {
    public static String[][] data = new String[][]{
        {"Account Settings", "https://randomuser.me/api/portraits/lego/1.jpg"},
        {"Privacy & Security", "https://randomuser.me/api/portraits/lego/2.jpg"},
        {"Notification", "https://randomuser.me/api/portraits/lego/3.jpg"},
        {"Transaction History", "https://randomuser.me/api/portraits/lego/4.jpg"},
        {"Payment Method", "https://randomuser.me/api/portraits/lego/5.jpg"},
        {"Join Us", "https://randomuser.me/api/portraits/lego/6.jpg"},
        {"Help Center", "https://randomuser.me/api/portraits/lego/7.jpg"},
        {"Log Out", "https://randomuser.me/api/portraits/lego/8.jpg"},
    };

    public static ArrayList<Profile> getListData(){
        Profile profile = null;
        ArrayList<Profile> list = new ArrayList<>();
        for (int i = 0; i <data.length; i++){
            profile = new Profile();
            profile.setName(data[i][0]);
            profile.setIcon(data[i][1]);
            list.add(profile);
        }
        return list;
    }

}
