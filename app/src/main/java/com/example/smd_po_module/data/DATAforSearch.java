package com.example.smd_po_module.data;

import com.example.smd_po_module.data.model.PO_DATA;

import java.util.ArrayList;

public class DATAforSearch {
    public static ArrayList<PO_DATA> getData(){
        ArrayList<PO_DATA> data= new ArrayList<>();

        String s= "Abiha Aftab";
        String s1= "Muntaha Zaigham";

        PO_DATA p= new PO_DATA();
            p.name=s;

        PO_DATA p1= new PO_DATA();
            p1.name=s1;

        data.add(p);
        data.add(p1);
        return data;
    }
}
