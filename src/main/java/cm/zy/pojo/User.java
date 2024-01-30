package cm.zy.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    @NotNull
    private Integer id;                   // ID principal
    private String username;              // Nombre de usuario
    @JsonIgnore
    private String password;              // Contraseña
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;              // Apodo o sobrenombre
    @NotEmpty
    @Email
    private String email;                 // Correo electrónico
    private String userPic;               // Dirección de la imagen de perfil del usuario
    private LocalDateTime createTime;    // Tiempo de creación
    private LocalDateTime updateTime;    // Tiempo de actualización
}
