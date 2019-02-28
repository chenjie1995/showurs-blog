package cn.showurs.blog.article.service.impl;

import cn.showurs.blog.article.entity.ArticleEntity;
import cn.showurs.blog.article.repository.ArticleRepository;
import cn.showurs.blog.article.service.ArticleService;
import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.common.vo.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by CJ on 2019/2/13 17:01.
 */
@Service
public class ArticleServiceImpl extends EntityServiceImpl<ArticleEntity, Article> implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).map(this::poToVo).orElse(null);
    }

    @Override
    public Page<Article> findPage(Pageable pageable) {
        return articleRepository.findAll(pageable).map(this::poToVo);
    }
}
