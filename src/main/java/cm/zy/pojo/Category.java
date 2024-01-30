package cm.zy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull(groups = {Update.class})
    private Integer id;                // ID principal
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryName;       // Nombre de la categoría
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryAlias;      // Alias de la categoría
    private Integer createUser;        // ID del creador
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;  // Tiempo de creación
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;  // Tiempo de actualización

    //  si alguno no ha asignado validación, se considera  un validación default

    public interface  Add extends Default {
    }
    public interface  Update extends Default{
    }
}