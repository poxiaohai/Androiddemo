package com.cfr.demo.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.delegationadapter.AdapterDelegate;

public class CompanyAdapterDelegate extends AdapterDelegate<String, CompanyAdapterDelegate.ViewHolder> {
    private  OnItemClieck mOnItemClieck;

    public void setmOnItemClieck(OnItemClieck mOnItemClieck) {
        this.mOnItemClieck = mOnItemClieck;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final String item) {
        holder.tvName.setText(item);
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClieck!=null) {
                    mOnItemClieck.Clieck(position);
                }
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(android.R.id.text1);
        }
    }


    public  interface  OnItemClieck{
        void  Clieck(int postion);
    }
}