package cm.zy.controller;

import cm.zy.pojo.Category;
import cm.zy.pojo.Result;
import cm.zy.service.CategorySerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategorySerice categorySerice;
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categorySerice.add(category);
        return Result.success();

    }
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> list = categorySerice.list();
        return Result.success(list);
    }
    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id){
        Category category = categorySerice.detail(id);
        return Result.success(category);

    }


    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categorySerice.update(category);
        return Result.success();
    }


    /**
     * delete category by id
     */
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        categorySerice.delete(id);
        return Result.success();
    }
}
