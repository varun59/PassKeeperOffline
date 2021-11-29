package com.passkeeperoffline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.passkeeperoffline.R;
import com.passkeeperoffline.db.AccountsModel;
import com.passkeeperoffline.utils.AdapterClickListener;
import com.passkeeperoffline.utils.Utils;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<AccountsModel> dataList;
    private AdapterClickListener adapterClickListener;

    public HomeAdapter(List<AccountsModel> dataList, AdapterClickListener adapterClickListener) {
        this.dataList = dataList;
        this.adapterClickListener = adapterClickListener;
    }

    public void updateData(List<AccountsModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.setData(dataList.get(position));
        holder.tvViewMore.setOnClickListener(v -> adapterClickListener.onClickListen(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmail, tvAccountName, tvViewMore;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAccountName = itemView.findViewById(R.id.tvAccountName);
            tvViewMore = itemView.findViewById(R.id.tvViewMore);
        }

        public void setData(AccountsModel accountsModel) {
            tvAccountName.setText(Utils.getInstance().decrypt(itemView.getContext(), accountsModel.accountName));
            tvEmail.setText(Utils.getInstance().decrypt(itemView.getContext(), accountsModel.email));


        }
    }
}
