package com.thesis.pdm.hallergen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerFamMember extends RecyclerView.Adapter<AdapterRecyclerFamMember.ViewHolder> {

    //Declairation
    private Context context;
    private static ArrayList<ModelsFamily> _familyList = new ArrayList<>();
    private OnClickFamilyMemberListener mClickFamilyMemberListener;
    private OnLongClickFamilyMemberListener mLongClickFamilyMemberListener;

    //Constructor
    public AdapterRecyclerFamMember(Context context,ArrayList<ModelsFamily> familyList,
                                    OnClickFamilyMemberListener clicklistener,
                                    OnLongClickFamilyMemberListener longClickListener) {
        this.context = context;
        this._familyList = familyList;
        this.mClickFamilyMemberListener = clicklistener;
        this.mLongClickFamilyMemberListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.con_item_family,parent,false),
                mClickFamilyMemberListener, mLongClickFamilyMemberListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.famMemberProfile.setImageResource(_familyList.get(position).getImageResourcesId());
        holder.famMemberName.setText(_familyList.get(position).getName()+"-"+Utility.getAge(_familyList.get(position).getBirthday()));
    }

    @Override
    public int getItemCount() { return _familyList == null? 0 : _familyList.size(); }

    //Class of the Item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView famMemberProfile;
        TextView  famMemberName;
        OnClickFamilyMemberListener clickFamilyMemberListener;
        OnLongClickFamilyMemberListener longClickFamilyMemberListener;

        public ViewHolder(@NonNull View itemView,
                          OnClickFamilyMemberListener onClickFamilyMemberListener,
                          OnLongClickFamilyMemberListener onLongClickFamilyMemberListener) {
            super(itemView);

            famMemberProfile    = itemView.findViewById(R.id.ivFamMemberProfile);
            famMemberName       = itemView.findViewById(R.id.tvFamMemberName);
            this.clickFamilyMemberListener = onClickFamilyMemberListener;
            this.longClickFamilyMemberListener = onLongClickFamilyMemberListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickFamilyMemberListener.OnClickFamilyMemberListener(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            longClickFamilyMemberListener.OnLongClickFamilyMemberListener(getAdapterPosition());
            return true;
        }
    }

    //Use to tap function
    public interface OnClickFamilyMemberListener{
        void OnClickFamilyMemberListener(int position);
    }

    //Use to long tap function
    public interface OnLongClickFamilyMemberListener{
        void OnLongClickFamilyMemberListener(int position);
    }
}
