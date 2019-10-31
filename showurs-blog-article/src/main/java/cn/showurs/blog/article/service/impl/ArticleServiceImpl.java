package cn.showurs.blog.article.service.impl;

import cn.showurs.blog.article.entity.ArticleEntity;
import cn.showurs.blog.article.repository.ArticleRepository;
import cn.showurs.blog.article.repository.SortRepository;
import cn.showurs.blog.article.service.ArticleService;
import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.vo.article.Article;
import cn.showurs.blog.common.vo.article.ArticlePublish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
        return articleRepository.findById(id).flatMap(this::poToVoOptional).orElse(new Article());
    }

    @Transactional
    @Override
    public Page<Article> findPage(Pageable pageable) {
        return articleRepository.findAll(pageable).map(this::poToVo);
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

    /**
     * 初始话文章信息
     * @param articlePublish 文章发布信息
     * @param author 作者ID
     * @return 文章实体
     */
    private ArticleEntity initArticleEntity(ArticlePublish articlePublish, Long author) {
        ArticleEntity articleEntity = voToPoOptional(articlePublish).orElseThrow(() -> new BusinessException("文章信息错误"));
        articleEntity.setAuthor(author);
        articleEntity.setSort(sortRepository.getOne(articlePublish.getSortId()));
        articleEntity.setCreateTime(LocalDateTime.now());
        articleEntity.setLastEditTime(LocalDateTime.now());
        articleEntity.setLastReplyTime(LocalDateTime.now());
        articleEntity.setClickCount(0L);
        return articleEntity;
    }
}
