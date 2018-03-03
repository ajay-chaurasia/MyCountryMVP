package com.example.ajaychaurasia.mycountrymvp.mvp.model;

import android.content.Context;


import com.example.ajaychaurasia.mycountrymvp.mvp.model.pojo.JSONResponseData;
import com.example.ajaychaurasia.mycountrymvp.mvp.presenter.CountryListPresenter;
import com.example.ajaychaurasia.mycountrymvp.mvp.view.CountryListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajay_chaurasia on 2/28/2018.
 */

public class CountryListPresenterImpl implements CountryListPresenter {

    private CountryListView countryListView;

    public CountryListPresenterImpl(CountryListView countryListView) {
        this.countryListView = countryListView;
    }

    @Override
    public void swipeRefresh(Context context) {
        queryListData(context);
    }

    @Override
    public void queryListData(Context context) {
        final Call<JSONResponseData> responseData = DropboxAPI.getService(context).getFactsData();
        responseData.enqueue(new Callback<JSONResponseData>() {
            @Override
            public void onResponse(Call<JSONResponseData> call, Response<JSONResponseData> response) {
                JSONResponseData restResponse = response.body();
                countryListView.showListView(restResponse);
            }

            @Override
            public void onFailure(Call<JSONResponseData> call, Throwable t) {
                countryListView.showErrorView(t.getMessage());
            }
        });

    }
}
