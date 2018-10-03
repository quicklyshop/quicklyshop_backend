package com.eci.cosw.project.quicklyshop.security.model;

public class RegistrationForm {

    User user;

    UserLogin userLogin;

    public RegistrationForm() {
        this(new User(), new UserLogin());
    }

    public RegistrationForm(User user, UserLogin userLogin) {
        this.user = user;
        this.userLogin = userLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
