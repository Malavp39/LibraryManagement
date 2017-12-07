package com.demo.validator;

import org.apache.commons.lang.StringUtils;

import com.demo.exception.UserException;
import com.demo.wrapper.UserWrapper;

public class UserValidator {

	private static final String ALPHABET_PATTERN = "^[a-zA-Z\\s]+$";

	private static final String DIGIT_PATTERN = "\\d+";

	public static void validateUserDetails(UserWrapper userDetail) {

		if (StringUtils.isEmpty(userDetail.getFirstName())) {
			throw new UserException("FIRST_NAME_EMPTY");
		}
		else if (!userDetail.getFirstName().matches(ALPHABET_PATTERN)) {
			throw new UserException("INVALID_FIRST_NAME_PATTERN");
		}
		else if (StringUtils.isEmpty(userDetail.getLastName())) {
			throw new UserException("LAST_NAME_EMPTY");
		}
		else if (!userDetail.getLastName().matches(ALPHABET_PATTERN)) {
			throw new UserException("INVALID_LAST_NAME_PATTERN");
		}
		else if (userDetail.getAge() <= 0) {
			throw new UserException("INVALID_AGE");
		}
		else if(StringUtils.isEmpty(userDetail.getGender())){
			throw new UserException("GENDER_MANDATORY");
		}
		else if(!userDetail.getGender().equals("M") && !userDetail.getGender().equals("F")){
			throw new UserException("INVALID_GENDER");
		}
		else if (!StringUtils.isEmpty(userDetail.getMiddleName()) && !userDetail.getMiddleName().matches(ALPHABET_PATTERN)) {
			throw new UserException("INVALID_MIDDLE_NAME_PATTERN");
		}
		else if(StringUtils.isEmpty(userDetail.getPhone())){
			throw new UserException("EMPTY_PHONE_NUMBER");
		}
		else if(!userDetail.getPhone().matches(DIGIT_PATTERN)){
			throw new UserException("INVALID_PHONE_NUMBER");
		}
		else if(userDetail.getPhone().trim().length() != 10){
			throw new UserException("INVALID_PHONE_NUMBER_LENGTH");
		}
	}

}
