package com.facebookCopy.facebookCopy.controller;

import com.facebookCopy.facebookCopy.model.*;
import com.facebookCopy.facebookCopy.service.serviceImp.CommentServiceImp;
import com.facebookCopy.facebookCopy.service.serviceImp.PostServiceImp;
import com.facebookCopy.facebookCopy.service.serviceImp.ProfileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fakefb/profile")
public class ProfileController {
    @Autowired
    ProfileServiceImp profileService;
    @Autowired
    PostServiceImp postService;
    @Autowired
    CommentServiceImp commentService;
@GetMapping("/get")
    public ResponseEntity<Profile> getProfile(){
    return profileService.getProfile();
}
@PostMapping("/post/create")
    public HttpStatus createPost(@RequestBody Post post){
    return profileService.createPost(post);
}
@DeleteMapping("/post/delete")
    public HttpStatus deletePost(@RequestBody Post post){
    return profileService.deletePost(post.getId());
}
@PutMapping("/post/update")
    public HttpStatus updatePost(@RequestBody Post post){
    return profileService.updatePost(post);
}
@PutMapping("/friend/request")
    public HttpStatus sendFriendRequest(@RequestBody Friend friend){
    return profileService.sendFriendRequest(friend.getId());
}
@PutMapping("/friend/accept")
    public HttpStatus acceptFriendRequest(@RequestBody Request request){
    return profileService.acceptFriendRequest(request);
}
    @GetMapping("/friend/registered")
    public ResponseEntity<List<Friend>> getAllFriendsTable(){
    return profileService.getAllFriendsTable();
    }
    @GetMapping("/requests/all")
    public ResponseEntity<List<Request>> getAllRequests(){
    return profileService.getAllRequests();
    }
    @GetMapping("/friend/all")
    public ResponseEntity<List<Friend>> getAllFriends(){
    return profileService.getAllFriends();
}
    @GetMapping("/post/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        return profileService.getAllPosts();
    }
    @PostMapping("/post/comment/add/{postId}")
    public HttpStatus addComment(@RequestBody Comment comment, @PathVariable int postId){
    return postService.addComment(comment,postId);
    }
    @DeleteMapping("/post/comment/remove/{postId}")
    public HttpStatus removeComment(@RequestBody Comment comment, @PathVariable int postId){
    return postService.deleteComment(comment.getId(),postId);
    }
    @PutMapping("/post/comment/update")
    public HttpStatus updateComment(@RequestBody Comment comment){
    return postService.updateComment(comment);
    }
    @PutMapping("/post/like/add/{postId}")
    public HttpStatus addLike(@PathVariable int postId){
    return postService.addLike(postId);
    }
    @PutMapping("/post/like/remove/{postId}")
    public HttpStatus removeLike(@PathVariable int postId){
    return postService.removeLike(postId);
    }
    @GetMapping("/post/comment/all/{postId}")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable int postId){
    return postService.getAllComments(postId);
    }
    @GetMapping("/post/comment/reply/all/{commentId}")
    public ResponseEntity<List<Reply>> getAllReply(@PathVariable int commentId){
    return commentService.getAllReply(commentId);
    }
    @PostMapping("/post/comment/reply/add/{commentId}")
    public HttpStatus addReply(@RequestBody Reply reply,@PathVariable int commentId){
    return commentService.addReply(reply,commentId);
    }
    @DeleteMapping("/post/comment/reply/remove/{commentId}")
    public HttpStatus removeReply(@RequestBody Reply reply, @PathVariable int commentId){
    return commentService.removeReply(reply.getId(),commentId);
    }
    @PutMapping("/post/comment/reply/update")
    public HttpStatus updateReply(@RequestBody Reply reply){
    return commentService.updateReply(reply);
    }
    @PutMapping("/post/comment/like/add/{commentId}")
    public HttpStatus addLikeComment(@PathVariable int commentId){
    return commentService.addLike(commentId);
    }
    @PutMapping("/post/comment/like/remove/{commentId}")
    public HttpStatus removeLikeComment(@PathVariable int commentId){
    return commentService.removeLike(commentId);
    }
}

