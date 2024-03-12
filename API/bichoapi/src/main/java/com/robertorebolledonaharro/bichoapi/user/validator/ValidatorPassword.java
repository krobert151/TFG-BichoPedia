package com.robertorebolledonaharro.bichoapi.user.validator;

import com.robertorebolledonaharro.bichoapi.user.dto.CreateUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidatorPassword implements ConstraintValidator<ValidPassword, CreateUserRequest> {

    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

    @Override
    public boolean isValid(CreateUserRequest createUserRequest, ConstraintValidatorContext constraintValidatorContext) {

        return textPattern.matcher(createUserRequest.password()).matches();

    }
}