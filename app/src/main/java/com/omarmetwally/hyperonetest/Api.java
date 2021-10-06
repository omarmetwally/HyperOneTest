package com.omarmetwally.hyperonetest;


import com.omarmetwally.hyperonetest.ui.ViewAll.ViewAllGetData_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("HyperoneWebservice/getListOfFilesResponse.json")
    Call<List<ViewAllGetData_Model>>getData();
}
