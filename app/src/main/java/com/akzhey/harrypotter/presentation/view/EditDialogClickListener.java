package com.akzhey.harrypotter.presentation.view;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;

public interface EditDialogClickListener {
    void onDialogPositiveClick(CharactersItem character, int position);
}
