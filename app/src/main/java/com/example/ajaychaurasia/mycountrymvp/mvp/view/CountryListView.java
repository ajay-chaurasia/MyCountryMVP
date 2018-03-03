package com.example.ajaychaurasia.mycountrymvp.mvp.view;


import com.example.ajaychaurasia.mycountrymvp.mvp.model.pojo.JSONResponseData;

/**
 * Created by ajay_chaurasia on 2/28/2018.
 */

public interface CountryListView {

    void updateActionTitleBar(String title);

    void showListView(JSONResponseData restResponse);

    void showErrorView(String message);
}
