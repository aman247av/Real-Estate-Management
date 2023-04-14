package com.example.dbms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbms.Model.Agent;

import java.util.List;


public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.ViewHolder> {
    List<Agent> agentDetailsList;
    Context context;


    public AgentAdapter(Context context, List<Agent> agentDetailsList) {
        this.context = context;
        this.agentDetailsList = agentDetailsList;
    }

    @NonNull
    @Override
    public AgentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agent, parent, false);
        AgentAdapter.ViewHolder viewHolder = new AgentAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AgentAdapter.ViewHolder holder, int position) {
        holder.tvAgentName.setText(agentDetailsList.get(position).getName());
        holder.tvEmail.setText(agentDetailsList.get(position).getE_mail() + " | "+agentDetailsList.get(position).getContact());
        holder.tvAddress.setText(agentDetailsList.get(position).getOffice_address());

        holder.whole_btn.setOnClickListener(v -> {
            Intent intent = new Intent(context, HomePage.class);
            intent.putExtra("agent_id", agentDetailsList.get(position).getAgent_id());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        return agentDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        
        RelativeLayout whole_btn;
        TextView tvAgentName,tvEmail,tvAddress;
        public ViewHolder(View view) {
            super(view);
            tvAgentName=(TextView) view.findViewById(R.id.tvAgentName);
            tvEmail=(TextView) view.findViewById(R.id.tvEmail);
            tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            whole_btn = view.findViewById(R.id.whole_btn);

        }
    }
}
