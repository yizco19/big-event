package cm.zy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Objeto para devolver resultados paginados
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Long total;      // Total de registros
    private List<T> items;   // Colección de datos de la página actual
}
