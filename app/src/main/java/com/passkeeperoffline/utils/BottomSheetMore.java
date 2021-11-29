package com.passkeeperoffline.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.passkeeperoffline.R;
import com.passkeeperoffline.db.AccountsModel;

public class BottomSheetMore extends BottomSheetDialogFragment {
    private static final String TAG = BottomSheetDialog.class.getSimpleName();
    private OnBottomItemClickListener onBottomItemClickListener;
    private AccountsModel account;
    private TextInputEditText etAccountName, etEmail, etPassword, etAdditional;
    private Context mContext;

    public BottomSheetMore(AccountsModel account, Context mContext) {
        this.account = account;
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottomsheet_view_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etAdditional = view.findViewById(R.id.etAdditional);
        etPassword = view.findViewById(R.id.etPassword);
        etEmail = view.findViewById(R.id.etEmail);
        etAccountName = view.findViewById(R.id.etAccountName);
        //*Gallery Click
        view.findViewById(R.id.btDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomItemClickListener.onDeleteClick();

                BottomSheetMore.this.dismiss();
            }
        });

        //*Camera Click
        view.findViewById(R.id.btEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomItemClickListener.onEditClick();
                BottomSheetMore.this.dismiss();
            }
        });
        setData();
    }

    private void setData() {
        etPassword.setText(Utils.getInstance().decrypt(mContext, account.password));
        etAccountName.setText(Utils.getInstance().decrypt(mContext, account.accountName));
        etEmail.setText(Utils.getInstance().decrypt(mContext, account.email));
        etAdditional.setText(Utils.getInstance().decrypt(mContext, account.additional));
    }

    public void setActionListener(OnBottomItemClickListener onBottomItemClickListener) {
        this.onBottomItemClickListener = onBottomItemClickListener;
    }

    public interface OnBottomItemClickListener {

        void onEditClick();

        void onDeleteClick();

    }
}
