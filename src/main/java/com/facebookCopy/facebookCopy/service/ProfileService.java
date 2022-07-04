package com.facebookCopy.facebookCopy.service;

import com.facebookCopy.facebookCopy.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
import java.util.List;

public interface ProfileService {
    HttpStatus register(Profile profile);
    ResponseEntity<Profile> getProfile();
    HttpStatus createPost(Post post);
    HttpStatus deletePost(Integer postId);
    HttpStatus updatePost(Post post);
    HttpStatus sendFriendRequest(Integer friendId);
    HttpStatus acceptFriendRequest(Request request);
    HttpStatus declineFriendRequest(Request request);
    ResponseEntity<List<Friend>> getAllFriends();
    ResponseEntity<List<Post>> getAllPosts();
    ResponseEntity<List<Friend>> getAllFriendsTable();
    ResponseEntity<List<Request>> getAllRequests();


}
