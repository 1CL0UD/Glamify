package com.project.glamify;

import java.util.ArrayList;

public class ProfileMenu {
    public static String[][] data = new String[][]{
        {"Account Settings", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Aperson%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Privacy & Security", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Asecurity%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Notification", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Anotifications%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Transaction History", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Apoint_of_sale%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Payment Method", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Apayments%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Join Us", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Awork%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Help Center", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Ahelp%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
        {"Log Out", "https://fonts.google.com/icons?selected=Material%20Symbols%20Outlined%3Alogout%3AFILL%400%3Bwght%40400%3BGRAD%400%3Bopsz%4048"},
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
