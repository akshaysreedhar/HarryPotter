package com.akzhey.harrypotter.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.akzhey.harrypotter.data.local.HarryPotterDao;
import com.akzhey.harrypotter.data.remote.HarryPotterApi;
import com.akzhey.harrypotter.data.remote.responses.CharactersItem;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HarryPotterRepository {

    private final HarryPotterApi harryPotterApi;
    private final HarryPotterDao harryPotterDao;

    @Inject
    public HarryPotterRepository(HarryPotterApi harryPotterApi, HarryPotterDao harryPotterDao) {
        this.harryPotterApi = harryPotterApi;
        this.harryPotterDao = harryPotterDao;
    }

    public LiveData<List<CharactersItem>> getHarryPotterCharacters() {
        MutableLiveData<List<CharactersItem>> charactersLiveData = new MutableLiveData<>();
        harryPotterApi.getHarryPotterCharacters().enqueue(new Callback<List<CharactersItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<CharactersItem>> call, @NonNull Response<List<CharactersItem>> response) {
                List<CharactersItem> characters;
                if (response.isSuccessful()) {
                    characters = response.body();
                } else {
                    characters = null;
                }
                charactersLiveData.setValue(characters);
            }

            @Override
            public void onFailure(@NonNull Call<List<CharactersItem>> call, @NonNull Throwable throwable) {
                charactersLiveData.setValue(null);
            }
        });
        return charactersLiveData;
    }


    public void insertCharacterList(List<CharactersItem> charactersItemList) {
        harryPotterDao.insertCharacters(charactersItemList);
    }

    public List<CharactersItem> getCharacterList() {
        return harryPotterDao.getAllCharacters();
    }

    public void updateCharacterList(List<CharactersItem> characters) {
        harryPotterDao.updateCharacters(characters);
    }

    public void deleteDB() {
        harryPotterDao.deleteAllCharacters();
    }
}
