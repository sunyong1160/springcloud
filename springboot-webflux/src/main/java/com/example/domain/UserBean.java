package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserBean implements Serializable {

    private Long id;
    private String username;
}
