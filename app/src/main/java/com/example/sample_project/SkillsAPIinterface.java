package com.example.sample_project;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillsAPIinterface {

    @GET("api/skilliq")
    // here they use list
    Call<List<GoogleLearningBoard>> getSkills_iq_GoogleLearnData() ;

}
