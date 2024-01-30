package cm.zy.service.impl;

import cm.zy.mapper.CategoryMapper;
import cm.zy.pojo.Category;
import cm.zy.service.CategorySerice;
import cm.zy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategorySerice {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        // agregar información de categoría
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);

    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Category> list = categoryMapper.list(userId);
        return list;
    }

    @Override
    public Category detail(Integer id) {
        Category category = categoryMapper.detail(id);
        return category;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
