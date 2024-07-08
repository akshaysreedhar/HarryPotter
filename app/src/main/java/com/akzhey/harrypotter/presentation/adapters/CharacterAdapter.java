package com.akzhey.harrypotter.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;
import com.akzhey.harrypotter.databinding.ItemCharacterBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final List<CharactersItem> characters = new ArrayList<>();
    private final OnCharacterClickListener listener;

    public CharacterAdapter(OnCharacterClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        CharactersItem character = characters.get(position);
        Glide.with(holder.itemView.getContext())
             .load(character.getImage())
             .transform(new CircleCrop())
             .into(holder.binding.ivCharacter);
        holder.binding.tvCharacterName.setText(character.getName());
        if (character.getActor().isEmpty()) {
            holder.binding.tvActorName.setVisibility(View.GONE);
        } else {
            holder.binding.tvActorName.setVisibility(View.VISIBLE);
            String actorName = "Acted by " + character.getActor();
            holder.binding.tvActorName.setText(actorName);
        }
        holder.itemView.setOnClickListener(v -> listener.onCharacterClick(character, position));
    }


    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        final ItemCharacterBinding binding;

        public CharacterViewHolder(@NonNull ItemCharacterBinding itemCharacterBinding) {
            super(itemCharacterBinding.getRoot());
            this.binding = itemCharacterBinding;
        }
    }

    public void setCharacters(List<CharactersItem> characterList) {
        characters.clear();
        characters.addAll(characterList);
        notifyDataSetChanged();
    }
}
