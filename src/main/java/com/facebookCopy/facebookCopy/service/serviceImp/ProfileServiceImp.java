package com.facebookCopy.facebookCopy.service.serviceImp;

import com.facebookCopy.facebookCopy.model.*;
import com.facebookCopy.facebookCopy.repository.*;
import com.facebookCopy.facebookCopy.security.*;
import com.facebookCopy.facebookCopy.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProfileServiceImp implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Override
    public HttpStatus register(Profile profile) {
        profile.setPassword(bCryptPasswordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);
        Friend friend=new Friend();
        friend.setFirstName(profile.getFirstName());
        friend.setLastName(profile.getLastName());
        friendRepository.save(friend);
        return HttpStatus.CREATED;
    }

    @Override
    public ResponseEntity<Profile> getProfile() {
        Profile profile=profileRepository.findByUserName(jwtRequestFilter.getUserName());
        return new ResponseEntity<Profile>(profile,HttpStatus.OK);
    }

    @Override
    public HttpStatus createPost(Post post) {
        post.setDatePosted(new Date());
       getProfile().getBody().getPostList().add(post);
       profileRepository.save(getProfile().getBody());
       return HttpStatus.CREATED;
    }

    @Override
    public HttpStatus deletePost(Integer postId) {
    Profile profile=getProfile().getBody();
    for(int index=0;index<=profile.getPostList().size()-1;index++){
        if(profile.getPostList().get(index).getId()==postId) {
            profile.getPostList().remove(index);
            break;
        }
    }
    profileRepository.save(profile);
    postRepository.deleteById(postId);
       return HttpStatus.OK;
    }

    @Override
    public HttpStatus updatePost(Post post) {
       postRepository.save(post);
       return HttpStatus.OK;
    }

    @Override
    public HttpStatus sendFriendRequest(Integer friendId) {
        Profile friendProfile=profileRepository.getById(friendId);

        Request request=new Request();
        request.setFriendId(getProfile().getBody().getId());
        friendProfile.getRequestList().add(request);
        profileRepository.save(friendProfile);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus acceptFriendRequest(Request request) {
        boolean condition=false;
        Profile profile=getProfile().getBody();
        List<Request> requests=profile.getRequestList();
        for(int index=0;index<=requests.size()-1;index++){
            if(requests.get(index).getId()==request.getId()&&requests.get(index).getFriendId()==request.getFriendId()){
                condition=true;
                getProfile().getBody().getRequestList().remove(index);
            }
        }
        if(condition) {
            Friend friend = friendRepository.findById(request.getFriendId()).get();
            Profile friendProfile = profileRepository.findById(request.getFriendId()).get();
            Friend hisFriend = friendRepository.findById(getProfile().getBody().getId()).get();
            friendProfile.setFriend(hisFriend);
            profile.setFriend(friend);
            profileRepository.save(friendProfile);
            profileRepository.save(profile);
        }
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus declineFriendRequest(Request request) {
        Profile profile=getProfile().getBody();
        List<Request> requests=profile.getRequestList();
        for(int index=0;index<=requests.size()-1;index++){
            if(requests.get(index).getId()==request.getId()&&requests.get(index).getFriendId()==request.getFriendId()){
                profile.getRequestList().remove(index);
            }
        }
        return HttpStatus.OK;
    }

    @Override
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<List<Post>>(getProfile().getBody().getPostList(),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Friend>> getAllFriendsTable() {
        return new ResponseEntity<List<Friend>>(friendRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Request>> getAllRequests() {
        return new ResponseEntity<List<Request>>(getProfile().getBody().getRequestList(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Friend>> getAllFriends() {
        return new ResponseEntity<List<Friend>>(getProfile().getBody().getFriendList(),HttpStatus.OK);
    }
}
