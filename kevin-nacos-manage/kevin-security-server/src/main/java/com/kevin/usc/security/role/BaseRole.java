package com.kevin.usc.security.role;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BaseRole implements Serializable {

    private Long id;
    private String roleName;

}
