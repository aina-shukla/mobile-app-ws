package com.mobileapp.userservice;

import com.mobileapp.ui.model.request.UserDetailsReqModel;
import com.mobileapp.ui.model.response.UserRest;

public interface UserService {

	UserRest createUsers(UserDetailsReqModel userReq);
}
