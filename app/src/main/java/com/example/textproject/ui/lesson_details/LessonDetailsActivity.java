package com.example.textproject.ui.lesson_details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.textproject.databinding.ActivityLessonDetailsBinding;
import com.example.textproject.db.entity.Lesson;

public class LessonDetailsActivity extends AppCompatActivity {
    private ActivityLessonDetailsBinding binding;
    public static String KEY_LESSON = "lesson";

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLessonDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Lesson lesson = (Lesson) getIntent().getSerializableExtra(KEY_LESSON);
        if (lesson != null) {
            binding.tvLessonTitle.setText(lesson.getTitle());
            binding.tvLessonDescription.setText(lesson.getDescription());
            binding.tvLessonUrl.setOnClickListener(view -> {
                Uri uriYoutubeLink = Uri.parse(lesson.getYoutubeLink());
                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, uriYoutubeLink);
                youtubeIntent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://com.google.android.youtube"));

                if (youtubeIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(youtubeIntent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, uriYoutubeLink);
                    startActivity(browserIntent);
                }
            });
        }
    }
}