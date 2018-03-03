package com.example.ajaychaurasia.mycountrymvp.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ajaychaurasia.mycountrymvp.R;
import com.example.ajaychaurasia.mycountrymvp.mvp.model.CountryListPresenterImpl;
import com.example.ajaychaurasia.mycountrymvp.mvp.model.pojo.JSONResponseData;
import com.example.ajaychaurasia.mycountrymvp.mvp.presenter.CountryListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ajay_chaurasia on 2/28/2018.
 */

public class CountryListViewFragment extends Fragment implements CountryListView {

    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.error_message)
    TextView errorMessage;

    private CountryListPresenter countryListPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_frag, container, false);
        ButterKnife.bind(this, view);
        errorMessage = view.findViewById(R.id.error_message);
        //This saves Fragment instance while configuration change
        setRetainInstance(true);
        countryListPresenter = new CountryListPresenterImpl(this);
        countryListPresenter.queryListData(getContext());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //REFRESH DATA HERE
                countryListPresenter.swipeRefresh(getContext());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void updateActionTitleBar(String title) {

    }

    @Override
    public void showListView(JSONResponseData restResponse) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        errorMessage.setText(restResponse.getTitle());
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView(String message) {
        if(swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }
}
