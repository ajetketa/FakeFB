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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Description;
    private String postImage;
    private Date datePosted;
    private int numberLikes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
