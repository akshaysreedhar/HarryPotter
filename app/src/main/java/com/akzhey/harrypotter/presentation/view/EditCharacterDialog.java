package com.akzhey.harrypotter.presentation.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;
import com.akzhey.harrypotter.databinding.DialogEditCharacterBinding;


public class EditCharacterDialog extends DialogFragment {

    private final CharactersItem character;
    private final EditDialogClickListener editDialogClickListener;
    private final int position;
    private DialogEditCharacterBinding binding;

    public EditCharacterDialog(EditDialogClickListener editDialogClickListener, CharactersItem character, int position) {
        this.character = character;
        this.editDialogClickListener = editDialogClickListener;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        binding = DialogEditCharacterBinding.inflate(inflater, null, false);
        binding.etCharacterName.setText(character.getName());
        binding.etActorName.setText(character.getActor());
        builder.setView(binding.getRoot())
                .setPositiveButton("Save", (dialog, id) -> {
                    Editable characterName = binding.etCharacterName.getText();
                    Editable actorName = binding.etActorName.getText();
                    if (characterName != null && actorName != null) {
                        saveCharacter(characterName.toString(), actorName.toString());
                    } else {
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> dismiss());
        return builder.create();
    }

    private void saveCharacter(String characterName, String actorName) {
        character.setName(characterName);
        character.setActor(actorName);
        editDialogClickListener.onDialogPositiveClick(character, position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
