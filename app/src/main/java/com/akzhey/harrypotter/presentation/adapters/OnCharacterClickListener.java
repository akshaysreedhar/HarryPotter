package com.akzhey.harrypotter.presentation.adapters;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;

public interface OnCharacterClickListener {
    void onCharacterClick(CharactersItem character, int position);
}
