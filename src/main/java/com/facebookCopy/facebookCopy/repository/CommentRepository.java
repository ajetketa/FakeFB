package com.facebookCopy.facebookCopy.repository;

import com.facebookCopy.facebookCopy.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
