package ru.itis.javalab.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.javalab.services.UsersService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Roman Leontev
 * 00:02 28.02.2021
 * group 11-905
 */

public class UniqEmailValidator implements ConstraintValidator<ValidUniqEmail, String> {

   @Autowired
   private UsersService usersService;

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      return usersService.userIsExist(value) != null;
   }
}
