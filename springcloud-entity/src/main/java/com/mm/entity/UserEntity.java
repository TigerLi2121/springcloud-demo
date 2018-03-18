package com.mm.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity {
    private long id;
    private String name;
    private int age;
    private LocalDateTime birthday;
}
