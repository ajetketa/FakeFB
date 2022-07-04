package com.facebookCopy.facebookCopy.service.serviceImp;

import com.facebookCopy.facebookCopy.model.Comment;
import com.facebookCopy.facebookCopy.model.Post;
import com.facebookCopy.facebookCopy.repository.CommentRepository;
import com.facebookCopy.facebookCopy.repository.PostRepository;
import com.facebookCopy.facebookCopy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Override
    public ResponseEntity<List<Comment>> getAllComments(int postId) {
        return new ResponseEntity<List<Comment>>(postRepository.findById(postId).get().getCommentList(), HttpStatus.OK);
    }

    @Override
    public HttpStatus addComment(Comment comment,int postId) {
        Post post=postRepository.findById(postId).get();
        post.getCommentList().add(comment);
        postRepository.save(post);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus updateComment(Comment comment) {
        commentRepository.save(comment);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus deleteComment(Integer commentId,int postId) {
        Post post=postRepository.findById(postId).get();
        post.getCommentList().remove(commentRepository.findById(commentId));
        postRepository.save(post);
        commentRepository.deleteById(commentId);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus addLike(int postId) {
        Post post=postRepository.findById(postId).get();
        post.setNumberLikes(post.getNumberLikes()+1);
        postRepository.save(post);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus removeLike(int postId) {
        Post post=postRepository.findById(postId).get();
        if(post.getNumberLikes()>0){
            post.setNumberLikes(post.getNumberLikes()-1);
        }
        postRepository.save(post);
        return HttpStatus.OK;
    }
}
