package com.example.textproject.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.textproject.db.entity.Lesson;

import java.util.List;

@Dao
public interface LessonsDao {
    @Insert
    void insertLesson(Lesson lesson);

    @Query("DELETE FROM Lesson WHERE lessonId = :lessonId")
    void deleteLesson(long lessonId);

    @Update
    void UpdateLesson(Lesson lesson);

    @Query("SELECT * FROM Lesson Where courseId =:courseId")
    LiveData<List<Lesson>> getLessonsByCourseId(long courseId);
    @Query("SELECT * FROM Lesson WHERE lessonId = :lessonId")
    LiveData<Lesson> getLessonById(long lessonId);

}
