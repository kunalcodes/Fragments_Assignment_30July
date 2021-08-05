package com.example.load_github_profiles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubFragment extends Fragment {

    private EditText mEtID;
    private Button mBtnSearch;
    private RecyclerView recyclerView;
    private List<ResponseModel> responseModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_github, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });
    }

    private void callApi() {
        ApiService apiService = Network.getInstance().create(ApiService.class);
        String user = mEtID.getText().toString();
        apiService.getUser(user).enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.body() != null){
                    responseModelList = response.body();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });
    }

    private void setAdapter() {
        ProfileAdapter profileAdapter = new ProfileAdapter(responseModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initViews(View view) {
        mEtID = view.findViewById(R.id.etID);
        mBtnSearch = view.findViewById(R.id.btnSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}