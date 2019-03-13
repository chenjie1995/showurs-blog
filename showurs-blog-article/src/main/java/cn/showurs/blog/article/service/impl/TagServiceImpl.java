package cn.showurs.blog.article.service.impl;

import cn.showurs.blog.article.entity.TagEntity;
import cn.showurs.blog.article.service.TagService;
import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.common.vo.article.Tag;
import org.springframework.stereotype.Service;

/**
 * Created by CJ on 2019/3/13 11:23.
 */
@Service
public class TagServiceImpl extends EntityServiceImpl<TagEntity, Tag> implements TagService {
}
