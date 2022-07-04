package com.facebookCopy.facebookCopy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int friendId;
}
