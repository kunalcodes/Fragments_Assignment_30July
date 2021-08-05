package com.example.load_github_profiles;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvImage;
    private TextView mTvLogin;
    private TextView mTvName;

    public ProfileViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvImage = itemView.findViewById(R.id.ivImage);
        mTvLogin = itemView.findViewById(R.id.tvLogin);
        mTvName = itemView.findViewById(R.id.tvName);
    }

    public void setData(ResponseModel responseModel) {
        if (responseModel.getOwner().getAvatarUrl() != null){
            Glide.with(mIvImage).load(Uri.parse(responseModel.getOwner().getAvatarUrl())).into(mIvImage);
        }
        mTvName.setText(responseModel.getFullName());
        mTvLogin.setText(responseModel.getOwner().getLogin());
    }
}
