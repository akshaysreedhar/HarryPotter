package com.akzhey.harrypotter.presentation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;
import com.akzhey.harrypotter.repository.HarryPotterRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharactersViewModel extends ViewModel {
    private LiveData<List<CharactersItem>> charactersLiveData;

    private final HarryPotterRepository harryPotterRepository;

    @Inject
    public CharactersViewModel(HarryPotterRepository harryPotterRepository) {
        this.harryPotterRepository = harryPotterRepository;
    }

    public LiveData<List<CharactersItem>> getHarryPotterCharacters() {
        if (charactersLiveData == null) {
            charactersLiveData = harryPotterRepository.getHarryPotterCharacters();
        }
        return charactersLiveData;
    }

    public void saveCharacterList(List<CharactersItem> charactersItemList) {
        harryPotterRepository.insertCharacterList(charactersItemList);
    }

    public List<CharactersItem> getCharacterList() {
        return harryPotterRepository.getCharacterList();
    }

    public void updateCharacterList(List<CharactersItem> characterList) {
        harryPotterRepository.updateCharacterList(characterList);
    }

    public void deleteDB() {
        harryPotterRepository.deleteDB();
    }
}
