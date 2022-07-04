package com.facebookCopy.facebookCopy.service;

import com.facebookCopy.facebookCopy.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
   ResponseEntity<List<Comment>> getAllComments(int postId);
   HttpStatus addComment(Comment comment,int postId);
   HttpStatus updateComment(Comment comment);
   HttpStatus deleteComment(Integer commentId,int postId);
   HttpStatus addLike(int postId);
   HttpStatus removeLike(int postId);

}
