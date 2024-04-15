package br.com.murilodev.rest.common.dto;

import java.io.Serializable;

public class UserIdentity implements Serializable {

    private String userId;

    public UserIdentity(String usuario) {
        this.userId = usuario;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
