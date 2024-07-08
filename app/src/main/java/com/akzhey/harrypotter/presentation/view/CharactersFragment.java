package com.akzhey.harrypotter.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.akzhey.harrypotter.data.remote.responses.CharactersItem;
import com.akzhey.harrypotter.databinding.FragmentCharactersBinding;
import com.akzhey.harrypotter.presentation.adapters.CharacterAdapter;
import com.akzhey.harrypotter.presentation.adapters.OnCharacterClickListener;
import com.akzhey.harrypotter.presentation.viewModel.CharactersViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharactersFragment extends Fragment implements OnCharacterClickListener, EditDialogClickListener {

    private FragmentCharactersBinding binding;
    private CharactersViewModel charactersViewModel;
    private CharacterAdapter characterAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupRecyclerView();
        observeCharacters();
    }

    /**
     * Setting up viewModel and deleting old db when loading for the first time
     */
    private void setupViewModel() {
        charactersViewModel = new ViewModelProvider(this).get(CharactersViewModel.class);
        charactersViewModel.deleteDB();
    }

    /**
     * Setting up recyclerView
     */
    private void setupRecyclerView() {
        characterAdapter = new CharacterAdapter(this);
        binding.rvCharacters.setAdapter(characterAdapter);
    }

    /**
     * Observer for character
     * If response is not null, or not empty, save the characters to db, else show error layout
     */
    private void observeCharacters() {
        charactersViewModel.getHarryPotterCharacters().observe(getViewLifecycleOwner(), charactersItems -> {
            if (charactersItems != null && !charactersItems.isEmpty()) {
                setLayout(false);
                charactersViewModel.saveCharacterList(charactersItems);
            } else {
                setLayout(true);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Showing different layouts for response error case and success case
     */
    private void setLayout(Boolean isError) {
        binding.piLoading.setVisibility(View.GONE);
        binding.rvCharacters.setVisibility(View.GONE);
        if (isError) {
            binding.clButton.setVisibility(View.GONE);
            binding.tvError.setVisibility(View.VISIBLE);
        } else {
            binding.clButton.setVisibility(View.VISIBLE);
            binding.tvError.setVisibility(View.GONE);
            binding.btLoad.setOnClickListener(view -> loadDataFromDB());
        }
    }

    /**
     * Loading data from db and showing data in recyclerview
     */
    private void loadDataFromDB() {
        List<CharactersItem> characterList = charactersViewModel.getCharacterList();
        characterAdapter.setCharacters(characterList);
        binding.clButton.setVisibility(View.GONE);
        binding.rvCharacters.setVisibility(View.VISIBLE);
    }

    /**
     * On clicking each item, open a Dialog in which we can edit the character
     */
    @Override
    public void onCharacterClick(CharactersItem character, int position) {
        new EditCharacterDialog(this, character, position)
                .show(requireActivity().getSupportFragmentManager(), "EDIT_DIALOG");
    }

    /**
     * On saving on edit, update data on db, and reload recyclerview
     */
    @Override
    public void onDialogPositiveClick(CharactersItem character, int position) {
        List<CharactersItem> characterList = charactersViewModel.getCharacterList();
        characterList.remove(position);
        characterList.add(position, character);
        charactersViewModel.updateCharacterList(characterList);
        characterAdapter.setCharacters(characterList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
