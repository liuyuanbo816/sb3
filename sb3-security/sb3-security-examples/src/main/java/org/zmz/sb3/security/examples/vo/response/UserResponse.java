package org.zmz.sb3.security.examples.vo.response;

import com.fasterxml.jackson.annotation.JsonView;

public class UserResponse {

    public interface UserResponseSimpleView {
    }

    public interface UserResponseDetailsView extends UserResponseSimpleView {
    }

    private String uid;
    private String name;
    private String password;

    @JsonView(UserResponseSimpleView.class)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonView(UserResponseSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserResponseDetailsView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
