package com.example.smd_po_module;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.smd_po_module.data.model.PO_DATA;

import java.util.ArrayList;

public class poSearchadapter extends RecyclerView.Adapter<poSearchadapter.MyViewHolder> {

   Context context;
   ArrayList<PO_DATA> data;

    public poSearchadapter(Context context, ArrayList<PO_DATA> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position){
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.recyclepo,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position){
        PO_DATA p= data.get(position);
        myViewHolder.textview1.setText(p.name);


    }
    @Override
    public int getItemCount(){
       // System.out.print(getdata.size());
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textview1;

        public MyViewHolder(View itemview)
        {
            super(itemview);
            itemview.setOnClickListener(this);
            context = itemview.getContext();
            textview1= (TextView) itemview.findViewById(R.id.tv2);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos == 0) {
                context.startActivity(new Intent(context, view_po.class));
            } else if (pos == 1) {
                context.startActivity(new Intent(context, view_po.class));
            }
            else
                context.startActivity(new Intent(context, view_po.class));
        }
    }
}
