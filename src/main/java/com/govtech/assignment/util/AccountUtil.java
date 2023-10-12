package com.govtech.assignment.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.govtech.assignment.dto.LoggedInUserAccountDTO;
import com.govtech.assignment.security.service.UserDetailsImpl;

public class AccountUtil {

	public static LoggedInUserAccountDTO getLoggedInUserAccountInfo() {
		LoggedInUserAccountDTO loggedInUserAccountDTO = new LoggedInUserAccountDTO();
		Authentication authentication = null;
		UserDetailsImpl userDetails = null;
		try {
			authentication = SecurityContextHolder.getContext().getAuthentication();
			userDetails = (UserDetailsImpl) authentication.getPrincipal();
		} catch (Exception e) {
		}
		if (userDetails != null) {
			loggedInUserAccountDTO.setAccountId(userDetails.getId());
			loggedInUserAccountDTO.setUserId(userDetails.getUserId());
			loggedInUserAccountDTO.setAccountType(userDetails.getType());
		}
		return loggedInUserAccountDTO;
	}
}
