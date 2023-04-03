package com.project.glamify;

import java.util.ArrayList;

public class ContactData {
    public static String[][] data = new String[][]{
        {"Simo Yanick", "https://randomuser.me/api/portraits/lego/1.jpg"},
        {"Timon Ladislav", "https://randomuser.me/api/portraits/lego/2.jpg"},
        {"Giove Julen", "https://randomuser.me/api/portraits/lego/3.jpg"},
        {"Vojtěch Jaffer", "https://randomuser.me/api/portraits/lego/4.jpg"},
        {"Gwallter Amar", "https://randomuser.me/api/portraits/lego/5.jpg"},
        {"Sage Gurgen", "https://randomuser.me/api/portraits/lego/6.jpg"},
        {"Eutychos Ælfræd", "https://randomuser.me/api/portraits/lego/7.jpg"},
        {"Thyge Leachlainn", "https://randomuser.me/api/portraits/lego/8.jpg"},
        {"Ariel Björn", "https://randomuser.me/api/portraits/lego/9.jpg"},
        {"Leucippus Mawuli", "https://randomuser.me/api/portraits/lego/0.jpg"}
    };

    public static ArrayList<Contact> getListData(){
        Contact contact = null;
        ArrayList<Contact> list = new ArrayList<>();
        for (int i = 0; i <data.length; i++){
            contact = new Contact();
            contact.setName(data[i][0]);
            contact.setPhoto(data[i][1]);
            list.add(contact);
        }
        return list;
    }

}
