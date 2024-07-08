package com.akzhey.harrypotter.di;

import android.app.Application;

import androidx.room.Room;

import com.akzhey.harrypotter.data.local.HarryPotterDB;
import com.akzhey.harrypotter.data.local.HarryPotterDao;
import com.akzhey.harrypotter.data.remote.HarryPotterApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static HarryPotterApi provideHarryPotterApiService() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hp-api.herokuapp.com/api/")
                .build()
                .create(HarryPotterApi.class);
    }

    @Provides
    @Singleton
    public static HarryPotterDB provideHarryPotterDB(Application application) {
        return Room.databaseBuilder(application, HarryPotterDB.class, "Character DB")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static HarryPotterDao provideHarryPotterDao(HarryPotterDB harryPotterDB) {
        return harryPotterDB.harryPotterDao();
    }
}
