package com.akzhey.harrypotter.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;

import java.util.List;

@Dao
public interface HarryPotterDao {

    @Query("SELECT * FROM charactersitem")
    List<CharactersItem> getAllCharacters();

    @Insert
    void insertCharacters(List<CharactersItem> characters);

    @Update
    void updateCharacters(List<CharactersItem> characters);

    @Query("DELETE FROM charactersitem")
    void deleteAllCharacters();
}
