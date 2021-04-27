package com.labawsrh.aws.rvitemanimaion;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class Model extends ViewModel {

    public Model() {
        super();

        // Log.e("model" , "created") ;
        repository = new Repository();

    }

    List<String> list;
    private OneToOneAdapter Adapter;
    Repository repository;


    public List<String> getUserList() {
        //   Log.e("Model", repository.getUsers().size() + "");
        return repository.getUsers();

    }


}

