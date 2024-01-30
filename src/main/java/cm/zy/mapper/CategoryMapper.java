package cm.zy.mapper;

import cm.zy.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * añadir categoría
     * @param category
     */
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            " values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
     void add(Category category);

    @Select("select * from category where create_user = #{id}")
    List<Category> list(Integer id);

    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias},update_time = #{updateTime}" +
            " where id = #{id}")
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void delete(Integer id);
}
