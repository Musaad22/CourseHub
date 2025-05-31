package com.example.textproject.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.textproject.databinding.FragmentProfileBinding;
import com.example.textproject.utils.SharedPrefsUtils;
import com.example.textproject.utils.Utils;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileFragmentViewModel viewModel;
    private SharedPrefsUtils prefsUtils;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ProfileFragmentViewModel.class);
        prefsUtils = new SharedPrefsUtils(requireContext());

        viewModel.getUserByUserId(prefsUtils.getUserId(), user -> {
            requireActivity().runOnUiThread(() -> {
                binding.nameTv.setText(user.getName());
                binding.emailET.setText(user.getEmail());
                binding.passwordEt.setText(user.getPassword());
            });
        });

        binding.saveButton.setOnClickListener(view -> {
            String name = binding.nameTv.getText().toString().trim();
            String email = binding.emailET.getText().toString().trim();
            String password = Objects.requireNonNull(binding.passwordEt.getText()).toString().trim();

            viewModel.updateUser(prefsUtils.getUserId(), name, email, password);
            Toast.makeText(getContext(), "Save Date", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}