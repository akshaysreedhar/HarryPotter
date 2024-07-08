package com.akzhey.harrypotter.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;

@Database(entities = {CharactersItem.class}, version = 1, exportSchema = false)
public abstract class HarryPotterDB extends RoomDatabase {
    public abstract HarryPotterDao harryPotterDao();
}
