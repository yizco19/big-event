package cm.zy.service;

import cm.zy.pojo.Article;
import cm.zy.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    // recuperar una lista paginada de artículos basada en los parámetros proporcionados.
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article detail(Integer id);

    void delete(Integer id);
}
