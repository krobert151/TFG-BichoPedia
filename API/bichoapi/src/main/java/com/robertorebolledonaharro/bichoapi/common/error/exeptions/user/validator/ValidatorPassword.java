package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.validator;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.dto.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidatorPassword implements ConstraintValidator<ValidPassword, RegisterDTO> {

    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

    @Override
    public boolean isValid(RegisterDTO createUserRequest, ConstraintValidatorContext constraintValidatorContext) {

        return textPattern.matcher(createUserRequest.password()).matches();

    }
}