package cn.showurs.blog.article.service.impl;

import cn.showurs.blog.article.entity.ArticleEntity;
import cn.showurs.blog.article.entity.TagEntity;
import cn.showurs.blog.article.repository.ArticleRepository;
import cn.showurs.blog.article.repository.SortRepository;
import cn.showurs.blog.article.repository.TagRepository;
import cn.showurs.blog.article.service.ArticleService;
import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.vo.article.Article;
import cn.showurs.blog.common.vo.article.ArticlePublish;
import cn.showurs.blog.common.vo.article.TagPublish;
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
    @Resource
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).map(this::poToVo).orElse(null);
    }

    @Transactional
    @Override
    public Page<Article> findPage(Pageable pageable) {
        return articleRepository.findAll(pageable).map(this::poToVo);
    }

    @Transactional
    @Override
    public Article publish(ArticlePublish articlePublish, Long author) {
        if (sortRepository.findById(articlePublish.getSortId()).isPresent()) {
            throw new BusinessException("文章分类不存在");
        }

        ArticleEntity articleEntity = voToPo(articlePublish);
        articleEntity.setAuthor(author);
        articleEntity.setCreateTime(LocalDateTime.now());
        articleEntity.setLastEditTime(LocalDateTime.now());
        articleEntity.setLastReplyTime(LocalDateTime.now());
        articleEntity.setClickCount(0L);
        articleEntity.setSort(sortRepository.getOne(articlePublish.getSortId()));

        if (articlePublish.getTagPublishes() != null && articlePublish.getTagPublishes().size() > 0) {
            for (TagPublish tagPublish : articlePublish.getTagPublishes()) {
                if (tagRepository.findByName(tagPublish.getName()).isPresent()) {
                    TagEntity tagEntity = new TagEntity();
                    tagEntity.setName(tagPublish.getName());
                }
            }
        }

        return null;
    }
}
