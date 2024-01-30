package cm.zy.pojo;


import cm.zy.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data
public class Article {
    private Integer id;            // Identificador principal (ID)
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;          // Título del artículo
    @NotEmpty
    private String content;        // Contenido del artículo
    @NotEmpty
    @URL
    private String coverImg;       // Imagen de portada
    @State  
    private String state;          // Estado de publicación: PUBLICADO|BORRADO
    @NotNull
    private Integer categoryId;    // ID de la categoría del artículo
    private Integer createUser;    // ID del creador
    private LocalDateTime createTime;  // Tiempo de creación
    private LocalDateTime updateTime;  // Tiempo de actualización
}