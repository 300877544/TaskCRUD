package com.dexlock.task.repository;

import com.dexlock.task.models.Comments;
import com.dexlock.task.models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
