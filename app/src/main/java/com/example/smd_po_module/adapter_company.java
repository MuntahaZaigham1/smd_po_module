package com.example.smd_po_module;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.company;
import com.example.smd_po_module.classes.information_company;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class adapter_company extends RecyclerView.Adapter<adapter_company.MyHolder>{
    Context context;
    ArrayList<company> getdata;
    public void setList(ArrayList<company> c){
        this.getdata=c;

    }
    public void refreshList(){
        notifyDataSetChanged();
    }
    public adapter_company(Context context, ArrayList<company> getdata) {
        this.context=context;
        this.getdata=getdata;
    }

    @NonNull
    @Override
    public adapter_company.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.company_object,parent,false);

        return new adapter_company.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_company.MyHolder holder, int position) {

        holder.cname.setText((getdata.get(position).getName()));
        holder.email.setText(getdata.get(position).getEmail());
        holder.cmmi.setText(getdata.get(position).getCmmi());



    }


    @Override
    public int getItemCount() {
        return getdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView cname;
        TextView email;
        TextView cmmi;
        CardView cv;
        public Context context1;

        public MyHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this::onClick);
            context = v.getContext();
            cv = itemView.findViewById(R.id.card);
            cname = itemView.findViewById(R.id.cname);
            email = itemView.findViewById(R.id.email);
            cmmi = itemView.findViewById(R.id.ccmi);

        }

        public void onClick(View v) {
            int viewId = v.getId();
            if(Global.intent == 'L') {
                int pos = getAdapterPosition();

                    Intent i=new Intent(context, view_company.class);
                     i.putExtra("cid",getdata.get(pos).getCid());
                    context.startActivity(i);

            }


        }
    }

}
