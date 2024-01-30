package cm.zy.service;

import cm.zy.pojo.Category;

import java.util.List;

public interface CategorySerice {
    void add(Category category);

    List<Category> list();

    Category detail(Integer id);

    void update(Category category);

    void delete(Integer id);
}
