package com.facebookCopy.facebookCopy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter

public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;
    private String comment;
    private Date datePosted;
    private String reply;
    private int numberLikes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reply> replyList;

}
