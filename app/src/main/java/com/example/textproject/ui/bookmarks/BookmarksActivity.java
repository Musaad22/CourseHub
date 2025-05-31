package com.example.textproject.ui.bookmarks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.textproject.databinding.ActivityBookMarksBinding;
import com.example.textproject.listeners.BookmarkItemListener;
import com.example.textproject.ui.adapters.BookmarksAdapter;
import com.google.android.material.snackbar.Snackbar;

public class BookmarksActivity extends AppCompatActivity implements BookmarkItemListener {
    private ActivityBookMarksBinding binding;
    private BookMarksActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookMarksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(BookMarksActivityViewModel.class);

        BookmarksAdapter adapter = new BookmarksAdapter(this);
        binding.rycBookMark.setLayoutManager(new LinearLayoutManager(this));
        binding.rycBookMark.setAdapter(adapter);

        viewModel.bookmarkedCourses.observe(this, adapter::submitList);
    }

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable deleteAction;

    @Override
    public void onDeleteClickListener(long bookmarkId, String courseTitle) {
        deleteAction = () -> viewModel.deleteBookmark(bookmarkId);
        handler.postDelayed(deleteAction, 5000);

        String message = "تم ازالة " + courseTitle + " من قائمة الكورسات المحفوظة";
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction("تراجع", v -> handler.removeCallbacks(deleteAction))
                .show();
    }
}