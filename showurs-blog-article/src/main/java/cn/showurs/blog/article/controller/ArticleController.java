package cn.showurs.blog.article.controller;

import cn.showurs.blog.article.service.ArticleService;
import cn.showurs.blog.common.annotation.Auth;
import cn.showurs.blog.common.annotation.CurrentUser;
import cn.showurs.blog.common.constant.RequestInfo;
import cn.showurs.blog.common.util.ResultGenerator;
import cn.showurs.blog.common.vo.Result;
import cn.showurs.blog.common.vo.article.Article;
import cn.showurs.blog.common.vo.article.ArticlePublish;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * Created by CJ on 2019/3/10 1:12.
 */
@Api(value = "文章", description = "文章相关接口😋")
@RestController
@RequestMapping(value = "articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ApiOperation("根据ID获取文章")
    @GetMapping("{id}")
    public Result<Article> getById(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(articleService.findById(id));
    }

    @ApiOperation("分页获取文章")
    @GetMapping("")
    public Result<Page<Article>> getPage(@Min(value = 1, message = "当页大小不能小于1") @RequestParam(required = false, defaultValue = "15") Integer pageSize,
                                         @Min(value = 1, message = "页码不能小于1") @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        return ResultGenerator.genSuccessResult(articleService.findPage(PageRequest.of(pageNum, pageSize)));
    }

    @Auth
    @ApiImplicitParam(value = "用户Token", paramType = "header", required = true, name = RequestInfo.HEADER_TOKEN_NAME, dataType = "String")
    @ApiOperation("发布文章")
    @PostMapping("")
    public Result<Article> publish(@Validated @RequestBody ArticlePublish articlePublish,
                                   @CurrentUser Long author) {
        return ResultGenerator.genSuccessResult(articleService.publish(articlePublish, author));
    }

    @Auth
    @ApiImplicitParam(value = "用户Token", paramType = "header", required = true, name = RequestInfo.HEADER_TOKEN_NAME, dataType = "String")
    @ApiOperation("删除文章")
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }
}
