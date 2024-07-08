package com.akzhey.harrypotter.data.remote.responses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class CharactersItem {

    @SerializedName("image")
    @ColumnInfo(name = "image_url")
    private String image;

    @SerializedName("actor")
    @ColumnInfo(name = "actor_name")
    private String actor;

    @SerializedName("name")
    @ColumnInfo(name = "character_name")
    private String name;

    @SerializedName("id")
    private String id;

    @PrimaryKey(autoGenerate = true)
    private int characterId;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActor() {
        return actor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }
}