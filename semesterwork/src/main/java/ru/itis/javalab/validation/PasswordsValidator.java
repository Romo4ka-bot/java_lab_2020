package ru.itis.javalab.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsValidator implements ConstraintValidator<ValidPasswords, Object> {

    private String password1PropertyName;
    private String password2PropertyName;

    @Override
    public void initialize(ValidPasswords constraintAnnotation) {
        this.password1PropertyName = constraintAnnotation.password1(); // название поля для name -> firstName
        this.password2PropertyName = constraintAnnotation.password2(); // название поля для surname -> lastName

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object password1 = new BeanWrapperImpl(value).getPropertyValue(password1PropertyName); //получили конкретные значения
        Object password2 = new BeanWrapperImpl(value).getPropertyValue(password2PropertyName);

        return password1 != null && password1.equals(password2);
    }
}
