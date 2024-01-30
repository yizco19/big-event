package cm.zy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;    // Código de estado de la operación: 0-Éxito, 1-Fracaso
    private String message;  // Mensaje de información
    private T data;          // Datos de respuesta

    // Método para retornar rápidamente un resultado exitoso con datos de respuesta
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Operación exitosa", data);
    }

    // Método para retornar rápidamente un resultado exitoso sin datos de respuesta
    public static Result success() {
        return new Result(0, "Operación exitosa", null);
    }

    // Método para retornar rápidamente un resultado de error con un mensaje personalizado
    public static Result error(String message) {
        return new Result(1, message, null);
    }

}