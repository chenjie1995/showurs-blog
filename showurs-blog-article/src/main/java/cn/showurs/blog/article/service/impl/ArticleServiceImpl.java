package cn.showurs.blog.article.service.impl;

import cn.showurs.blog.article.entity.ArticleEntity;
import cn.showurs.blog.article.entity.ArticleTagEntity;
import cn.showurs.blog.article.entity.TagEntity;
import cn.showurs.blog.article.repository.ArticleRepository;
import cn.showurs.blog.article.repository.ArticleTagRepository;
import cn.showurs.blog.article.repository.SortRepository;
import cn.showurs.blog.article.repository.TagRepository;
import cn.showurs.blog.article.service.ArticleService;
import cn.showurs.blog.article.service.TagService;
import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.vo.article.Article;
import cn.showurs.blog.common.vo.article.ArticlePublish;
import cn.showurs.blog.common.vo.article.TagPublish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by CJ on 2019/2/13 17:01.
 */
@Service
public class ArticleServiceImpl extends EntityServiceImpl<ArticleEntity, Article> implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private SortRepository sortRepository;

    @Transactional
    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).flatMap(this::poToVo).orElse(new Article());
    }

    @Transactional
    @Override
    public Page<Article> findPage(Pageable pageable) {
        return articleRepository.findAll(pageable).map(po -> this.poToVo(po).orElse(null));
    }

    @Transactional
    @Override
    public Article publish(ArticlePublish articlePublish, Long author) {
       return null;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    private ArticleEntity initArticleEntity(ArticlePublish articlePublish, Long author) {
        ArticleEntity articleEntity = voToPo(articlePublish).orElseThrow(() -> new BusinessException("文章信息错误"));
        articleEntity.setAuthor(author);
        articleEntity.setSort(sortRepository.getOne(articlePublish.getSortId()));
        articleEntity.setCreateTime(LocalDateTime.now());
        articleEntity.setLastEditTime(LocalDateTime.now());
        articleEntity.setLastReplyTime(LocalDateTime.now());
        articleEntity.setClickCount(0L);
        return articleEntity;
    }
}
