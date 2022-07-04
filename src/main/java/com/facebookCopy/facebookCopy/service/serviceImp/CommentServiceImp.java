package com.facebookCopy.facebookCopy.service.serviceImp;

import com.facebookCopy.facebookCopy.model.Comment;
import com.facebookCopy.facebookCopy.model.Reply;
import com.facebookCopy.facebookCopy.repository.CommentRepository;
import com.facebookCopy.facebookCopy.repository.ReplyRepository;
import com.facebookCopy.facebookCopy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReplyRepository replyRepository;

    @Override
    public ResponseEntity<List<Reply>> getAllReply(int commentId) {
        List<Reply> replies=commentRepository.findById(commentId).get().getReplyList();
        return new ResponseEntity<List<Reply>>(replies, HttpStatus.OK);
    }

    @Override
    public HttpStatus addReply(Reply reply, int commentId) {
        Comment comment=commentRepository.findById(commentId).get();
        comment.getReplyList().add(reply);
        commentRepository.save(comment);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus removeReply(int replyId, int commentId) {
        Comment comment=commentRepository.findById(commentId).get();
        comment.getReplyList().remove(replyRepository.findById(replyId).get());
        commentRepository.save(comment);
        replyRepository.deleteById(replyId);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus updateReply(Reply reply) {
        replyRepository.save(reply);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus addLike(int commentId) {
        Comment comment=commentRepository.findById(commentId).get();
        comment.setNumberLikes(comment.getNumberLikes()+1);
        commentRepository.save(comment);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus removeLike(int commentId) {
        Comment comment=commentRepository.findById(commentId).get();
        if(comment.getNumberLikes()>0){
            comment.setNumberLikes(comment.getNumberLikes()-1);
            commentRepository.save(comment);
        }
        return HttpStatus.OK;
    }


}
