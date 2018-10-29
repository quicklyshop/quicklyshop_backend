package com.eci.cosw.project.quicklyshop.security.service.profile;

import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.profile.exceptions.ProfileServiceException;

public interface ProfileService {

    void updateUserProfile(User userContent) throws ProfileServiceException;

    User getProfile(String username) throws ProfileServiceException;
}
