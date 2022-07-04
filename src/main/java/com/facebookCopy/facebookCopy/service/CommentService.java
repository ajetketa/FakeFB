package com.facebookCopy.facebookCopy.service;

import com.facebookCopy.facebookCopy.model.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
import java.util.List;

public interface CommentService {
    ResponseEntity<List<Reply>> getAllReply(int commentId);
    HttpStatus addReply(Reply reply, int commentId);
    HttpStatus removeReply(int replyId, int commentId);
    HttpStatus updateReply(Reply reply);
    HttpStatus addLike(int commentId);
    HttpStatus removeLike(int commentId);
}
