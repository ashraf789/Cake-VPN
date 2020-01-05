package com.lazycoder.cakevpn.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lazycoder.cakevpn.R;
import com.lazycoder.cakevpn.model.ServerList;

import java.util.ArrayList;

public class ServerListRVAdapter extends RecyclerView.Adapter<ServerListRVAdapter.MyViewHolder> {

    private ArrayList<ServerList> serverLists;
    private Context mContext;

    public ServerListRVAdapter(ArrayList<ServerList> serverLists, Context context) {
        this.serverLists = serverLists;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.server_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.serverCountry.setText(serverLists.get(position).getCountry());
        Glide.with(mContext)
                .load(serverLists.get(position).getFlagUrl())
                .into(holder.serverIcon);

        holder.serverItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return serverLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout serverItemLayout;
        ImageView serverIcon;
        TextView serverCountry;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            serverItemLayout = itemView.findViewById(R.id.serverItemLayout);
            serverIcon = itemView.findViewById(R.id.iconImg);
            serverCountry = itemView.findViewById(R.id.countryTv);
        }
    }
}
