package cm.zy.valitadtion;

import cm.zy.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {

    /**
     *
     * @param value El valor a validar.
     * @param constraintValidatorContext El contexto del proceso de validación
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }
        if(value.equals("PUBLICADO") || value.equals("BORRADOR")){
            return true;
        }
        return false;
    }
}
