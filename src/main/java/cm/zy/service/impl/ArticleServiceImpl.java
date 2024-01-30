package cm.zy.service.impl;

import cm.zy.mapper.ArticleMapper;
import cm.zy.pojo.Article;
import cm.zy.pojo.PageBean;
import cm.zy.service.ArticleService;
import cm.zy.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // crea una instancia de PageBean
        PageBean<Article> pb = new PageBean<>();

        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);
        // Page ofrece metodo para obtener el total de registros y la lista de registros
        Page<Article> p =(Page<Article>)as;

        //
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Article detail(Integer id) {
        Article article = articleMapper.detail(id);
        return article;
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
