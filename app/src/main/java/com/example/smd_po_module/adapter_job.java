package com.example.smd_po_module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.information_job;

import java.util.ArrayList;

public class adapter_job extends RecyclerView.Adapter<adapter_job.MyHolder>{
    Context context;
    ArrayList<information_job> getdata;
    public adapter_job(Context context, ArrayList<information_job> getdata) {
        this.context=context;
        this.getdata=getdata;
    }

    @NonNull
    @Override
    public adapter_job.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.job_object,parent,false);

        return new adapter_job.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_job.MyHolder holder, int position) {
        holder.pos.setText((getdata.get(position).title[0]));
        holder.cname.setText(getdata.get(position).title[1]);
        holder.salary.setText(getdata.get(position).title[2]);



    }

    @Override
    public int getItemCount() {
        return getdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView pos;
        TextView cname;
        TextView salary;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            pos= itemView.findViewById(R.id.pos);
           cname= itemView.findViewById(R.id.cname);
            salary= itemView.findViewById(R.id.salary);
        }
    }

}
