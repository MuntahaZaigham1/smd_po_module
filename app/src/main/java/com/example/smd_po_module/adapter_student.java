package com.example.smd_po_module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.information_stud;
import com.example.smd_po_module.classes.job;
import com.example.smd_po_module.classes.student;

import java.util.ArrayList;

public class adapter_student extends RecyclerView.Adapter<adapter_student.MyHolder> {

    Context context;
    ArrayList<student> getdata;
    public void setList(ArrayList<student> c){
        this.getdata=c;

    }
    public void refreshList(){
        notifyDataSetChanged();
    }
    public adapter_student(Context context, ArrayList<student> getdata) {
        this.context=context;
        this.getdata=getdata;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.student_object,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.sname.setText((getdata.get(position).getName()));
        holder.batch.setText(getdata.get(position).getDegree());
        holder.cgpa.setText(getdata.get(position).getGpa());



    }

    @Override
    public int getItemCount() {
        return getdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView sname;
        TextView batch;
        TextView cgpa;
        CardView cv;
        AlertDialog.Builder builder;
        public MyHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this::onClick);
            context = v.getContext();
            cv = itemView.findViewById(R.id.card);
            sname= itemView.findViewById(R.id.sname);
            batch= itemView.findViewById(R.id.batch);
            cgpa= itemView.findViewById(R.id.cgpa);
            builder = new AlertDialog.Builder(context);
        }

        public void onClick(View v) {
            int viewId = v.getId();
            if(Global.intent == 'S') {
                int pos = getAdapterPosition();
                Intent i= new Intent(context, show_student.class);
                i.putExtra("sid",getdata.get(pos).getId());
                context.startActivity(i);
            }
            if(Global.intent == 'T'){
                Global.intent1='T';
                int pos = getAdapterPosition();
                if (pos == 0) {

                    context.startActivity(new Intent(context, list_Jobs.class));
                } else if (pos == 1) {
                    context.startActivity(new Intent(context, list_Jobs.class));
                } else if (pos == 2) {
                    context.startActivity(new Intent(context, list_Jobs.class));
                } else if (pos == 3) {
                    context.startActivity(new Intent(context, list_Jobs.class));
                }
            }
            if(Global.intent == 'i'){


            }
            if(Global.intent == 'N'&& Global.intent1=='c'){

                int pos = getAdapterPosition();

                if (pos == 0) {
                    Intent i=new Intent(context, chat_base.class);
                    Bundle extras = new Bundle();
                    extras.putString("r_uid", "L164329");
                    extras.putString("r_name","Abiha");
                    context.startActivity(i);
                   // context.startActivity(new Intent(context, chat_base.class));
                } else if (pos == 1) {
                    Intent i=new Intent(context, chat_base.class);
                    Bundle extras = new Bundle();
                    extras.putString("r_uid", "L164329");
                    extras.putString("r_name","Abiha");
                    context.startActivity(i);
                    //  context.startActivity(new Intent(context, chat_base.class));
                } else if (pos == 2) {
                    Intent i=new Intent(context, chat_base.class);
                    Bundle extras = new Bundle();
                    extras.putString("r_uid", "L164329");
                    extras.putString("r_name","Abiha");
                    context.startActivity(i);
                    //context.startActivity(new Intent(context, chat_base.class));
                } else if (pos == 3) {
                    Intent i=new Intent(context, chat_base.class);
                    Bundle extras = new Bundle();
                    String r_uid="L164329";
                    String r_name="Abiha";
                    extras.putString("r_uid",r_uid );
                    extras.putString("r_name",r_name);
                    context.startActivity(i);
                    //context.startActivity(new Intent(context, chat_base.class));
                }
            }


        }
    }
}
