package com.thesis.pdm.hallergen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerContact extends RecyclerView.Adapter<AdapterRecyclerContact.ViewHolder> {

    //Declairation
    private Context context;
    private static ArrayList<ModelsContact> _contactsList = new ArrayList<>();
    private OnLongClickContactListener mLongClickContactListener;

    // Constructor
    public AdapterRecyclerContact(Context context, ArrayList<ModelsContact> contactList,
                                  OnLongClickContactListener longClickListener) {
        this.context = context;
        this._contactsList = contactList;
        this.mLongClickContactListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.con_item_contact,parent,false),
                mLongClickContactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.conName.setText(_contactsList.get(position).getContactName());
        holder.conNumber.setText(_contactsList.get(position).getContactNumber());
        holder.conRelation.setText(_contactsList.get(position).getContactRelation());
        holder.switchReceive.setChecked(_contactsList.get(position).isReceiveMessage());
    }

    @Override
    public int getItemCount() { return _contactsList == null? 0 : _contactsList.size(); }

    //Class of the Item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView conName;
        TextView conNumber;
        TextView conRelation;
        Switch switchReceive;
        OnLongClickContactListener longClickContactListener;

        public ViewHolder(@NonNull View itemView , OnLongClickContactListener onLongClickContactListener) {
            super(itemView);

            conName         = itemView.findViewById(R.id.tvContactName);
            conNumber       = itemView.findViewById(R.id.tvContactNo);
            conRelation     = itemView.findViewById(R.id.tvContactRelation);
            switchReceive   = itemView.findViewById(R.id.switchSend);
            this.longClickContactListener = onLongClickContactListener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            longClickContactListener.OnLongClickContactListener(getAdapterPosition());
            return true;
        }
    }

    //Use to long tap function
    public interface OnLongClickContactListener{
        void OnLongClickContactListener(int position);
    }
}
