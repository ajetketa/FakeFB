package com.facebookCopy.facebookCopy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateBirth;
    private String password;
    private String profilePicture;
    private String profileWallpaper;
    @OneToMany(cascade=CascadeType.ALL)

    private List<Request> requestList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> postList;
    @ManyToMany
    private List<Friend> friendList;
    public void setFriend(Friend friend){
        friendList.add(friend);
    }


}
