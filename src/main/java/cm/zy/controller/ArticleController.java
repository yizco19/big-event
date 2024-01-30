package cm.zy.controller;

import cm.zy.pojo.Article;
import cm.zy.pojo.PageBean;
import cm.zy.service.ArticleService;
import cm.zy.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cm.zy.pojo.Result;

import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
   /* @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization" ) String token, HttpServletResponse response){

        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("todos los articulos..");
        }catch (Exception e){
            // token invalido y http status 401
            return Result.error("no autorizado");
        return Result.success("todos los articulos..");
    }*/

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();

    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id){

        Article article = articleService.detail(id);
        return Result.success(article);

    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        articleService.delete(id);
        return Result.success();
    }

}
