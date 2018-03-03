package com.example.ajaychaurasia.mycountrymvp.mvp.presenter;

import android.content.Context;

/**
 * Created by ajay_chaurasia on 2/28/2018.
 */

public interface CountryListPresenter {

    void swipeRefresh(Context context);
    void queryListData(Context context);
}
