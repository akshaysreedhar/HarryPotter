package com.akzhey.harrypotter.data.remote;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HarryPotterApi {

    @GET("characters/students")
    Call<List<CharactersItem>> getHarryPotterCharacters();
}
