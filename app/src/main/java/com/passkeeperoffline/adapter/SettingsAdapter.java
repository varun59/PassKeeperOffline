package com.passkeeperoffline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.passkeeperoffline.R;
import com.passkeeperoffline.db.AccountsModel;
import com.passkeeperoffline.utils.AdapterClickListener;
import com.passkeeperoffline.utils.Constants;
import com.passkeeperoffline.utils.PreferencesHelper;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {
    private List<String> dataList;
    private AdapterClickListener adapterClickListener;

    public SettingsAdapter(List<String> dataList, AdapterClickListener adapterClickListener) {
        this.dataList = dataList;
        this.adapterClickListener = adapterClickListener;
    }

    public void updateData(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SettingsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_settings_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        holder.tvSettings.setText(dataList.get(position));
        holder.itemView.setOnClickListener(view -> adapterClickListener.onClickListen(position));
        if (dataList.get(position).contains("Biometric")) {
            holder.swBiometric.setVisibility(View.VISIBLE);
            holder.tvSettings.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            if (BiometricManager.from(holder.itemView.getContext()).canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS) {
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class SettingsViewHolder extends RecyclerView.ViewHolder {
        TextView tvSettings;
        SwitchMaterial swBiometric;

        public SettingsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSettings = itemView.findViewById(R.id.tvSettings);
            swBiometric = itemView.findViewById(R.id.swBiometric);
            if (PreferencesHelper.getInstance().getBiometric() == 1)
                swBiometric.setChecked(true);
            swBiometric.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    PreferencesHelper.getInstance().setBiometric(1);
                } else PreferencesHelper.getInstance().setBiometric(0);

            });
        }
    }
}
