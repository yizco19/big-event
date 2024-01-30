package cm.zy.anno;


import cm.zy.valitadtion.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // docmentacion de la anotacion
@Constraint(
        validatedBy = {StateValidation.class}
) // la anotacion se aplica a una clase
@Target({ ElementType.FIELD})// la anotacion solo se aplica a atributos
@Retention(RetentionPolicy.RUNTIME) // tiempo de ejecucion de la anotacion
public @interface State {

    // Especifica el mensaje de error que se debe mostrar si la validación falla
    String message() default " el valor de state solo puede ser publicado o borrador";

    // Especifica los grupos de restricciones a los que pertenece la anotación
    Class<?>[] groups() default {};
    // Especifica el payload(contiene información adicional sobre la falla de validacion) que debe asociarse con la anotación
    Class<? extends Payload>[] payload() default {};
}
