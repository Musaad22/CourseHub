package com.example.textproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.textproject.databinding.FragmentHomeBinding;
import com.example.textproject.db.entity.Category;
import com.example.textproject.ui.adapters.CategoryAdapter;
import com.example.textproject.ui.category.CategoryFragment;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeFragmentViewModel viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        viewModel.getAllCategories(categories -> {
            ArrayList<CategoryFragment> fragments = new ArrayList<>();
            for (Category category : categories) {
                fragments.add(CategoryFragment.newInstance(category.getCategoryId()));
            }
            HomeFragment.this.requireActivity().runOnUiThread(() -> {
                CategoryAdapter adapter = new CategoryAdapter(this, fragments);
                binding.viewPager2.setAdapter(adapter);
                new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) ->
                        tab.setText(categories.get(position).getName())
                ).attach();
            });
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}