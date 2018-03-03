
package com.example.ajaychaurasia.mycountrymvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ajaychaurasia.mycountrymvp.mvp.view.CountryListViewFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CountryListViewFragment listViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Adding the ListView Fragment to MainActivity
        * This also queries data for the first time on activity creation
        * on Orientation change, fragment's old instance is reused */
        if (findViewById(R.id.fragment_container) != null) {
            listViewFragment = (CountryListViewFragment) getSupportFragmentManager().findFragmentByTag(getResources().getString(R.string.list_fragment_tag));
            if (listViewFragment == null) {
                initiateFragment();
            }
        }
    }

    /* Callback method to update ActionBar title
    *  Arguments can be null or a string value
    *  When Null string is passed, title set is app name
    */
    /*@Override
    public void updateActionBarTitle(String title) {
        if (null != title) {
            getSupportActionBar().setTitle(title);
        } else {
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }*/

    // This methods creates a new instance of CountryListViewFragment and attaches it to MainActivity
    public void initiateFragment() {
        listViewFragment = new CountryListViewFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, listViewFragment, getResources().getString(R.string.list_fragment_tag))
                .commit();
    }
}
