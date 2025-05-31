package com.example.textproject.ui.my_courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.textproject.databinding.FragmentMyCoursesBinding;
import com.example.textproject.ui.adapters.MyCoursesStateAdapter;
import com.example.textproject.ui.completed_courses.CompletedCoursesFragment;
import com.example.textproject.ui.ongoing_courses.OngoingCoursesFragment;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MyCoursesFragment extends Fragment {
    private FragmentMyCoursesBinding binding;

    public MyCoursesFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyCoursesBinding.inflate(inflater, container, false);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OngoingCoursesFragment());
        fragments.add(new CompletedCoursesFragment());

        MyCoursesStateAdapter adapter = new MyCoursesStateAdapter(this, fragments);
        binding.viewPagerMyCourses.setAdapter(adapter);

        new TabLayoutMediator(binding.tabMyCourses, binding.viewPagerMyCourses, (tab, position) -> {
            if (position == 0) {
                tab.setText("Ongoing");
            } else {
                tab.setText("Completed");
            }
        }).attach();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}