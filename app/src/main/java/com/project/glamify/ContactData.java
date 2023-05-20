package com.project.glamify;

import java.util.ArrayList;

public class ContactData {
    public static String[][] data = new String[][]{
        {"Wedding Dress", "https://i.pinimg.com/564x/9a/11/94/9a119464c6341ea850cf1853d72c122f.jpg"},
        {"Lakeside Wedding", "https://i.pinimg.com/564x/19/34/f2/1934f2cdd34478bdfa0b92aed4cb686d.jpg"},
        {"Beach Wedding", "https://i.pinimg.com/564x/1a/75/93/1a7593a91d3ea833a96b3fc9fbf7e1ad.jpg"},
        {"Sea View Wedding", "https://i.pinimg.com/564x/62/45/c3/6245c3268aa39e6f7a71cd7cc6189a61.jpg"},
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
