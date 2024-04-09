package com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.validator;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Documented
@Constraint(validatedBy = ValidatorPassword.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword{

    String message() default "Runs invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
