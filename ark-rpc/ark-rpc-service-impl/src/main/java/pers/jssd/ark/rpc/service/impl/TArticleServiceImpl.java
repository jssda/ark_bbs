package pers.jssd.ark.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.mapper.*;
import pers.jssd.ark.rpc.pojo.*;
import pers.jssd.ark.rpc.service.TArticleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TArticleServiceImpl implements TArticleService {

    private final Logger logger = Logger.getLogger(TUserInfoServiceImpl.class);

    private final TArticleMapper articleMapper;
    private final TUserInfoMapper userInfoMapper;
    private final TArtTypeArtMapper artTypeArtMapper;
    private final TArticleTypeMapper articleTypeMapper;
    private final TSectionMapper sectionMapper;
    private final TCommentMapper commentMapper;

    public TArticleServiceImpl(TArticleMapper articleMapper, TUserInfoMapper userInfoMapper, TArtTypeArtMapper artTypeArtMapper, TArticleTypeMapper articleTypeMapper, TSectionMapper sectionMapper, TCommentMapper commentMapper) {
        this.articleMapper = articleMapper;
        this.userInfoMapper = userInfoMapper;
        this.artTypeArtMapper = artTypeArtMapper;
        this.articleTypeMapper = articleTypeMapper;
        this.sectionMapper = sectionMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public PageInfo<TArticle> selectArticleByPageNum(PageNum pageNum) {
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TArticleExample articleExample = new TArticleExample();
        List<TArticle> tArticles = articleMapper.selectByExampleWithBLOBs(articleExample);

        getUserInfo(tArticles.toArray(new TArticle[0]));
        getSection(tArticles.toArray(new TArticle[0]));

        return new PageInfo<TArticle>(tArticles);
    }

    /**
     * 封装完整的文章数据, 将文章表中不存在的用户信息封装进文章表中
     *
     * @param tArticles 不完整的文章信息, 必须包含文章的id
     */
    private void getUserInfo(TArticle... tArticles) {
        if (tArticles != null && tArticles.length != 0) {
            for (TArticle tArticle : tArticles) {
                if (tArticle != null) {
                    Integer artUserId = tArticle.getArtUserId();
                    TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(artUserId);
                    tArticle.setUser(tUserInfo);
                }
            }
        }
    }

    /**
     * 获取文章对应的板块信息
     *
     * @param tArticles 文章容器
     */
    private void getSection(TArticle... tArticles) {
        for (TArticle tArticle : tArticles) {
            if (tArticle != null) {
                Integer artSecId = tArticle.getArtSecId();
                TSection tSection = sectionMapper.selectByPrimaryKey(artSecId);
                tArticle.setSection(tSection);
            }
        }
    }

    @Override
    public PageInfo<TArticle> selectArticleByPageNumAndKey(PageNum pageNum, String key) {
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        key = "%" + key + "%";

        TUserInfoExample userInfoExample = new TUserInfoExample();
        TUserInfoExample.Criteria userInfoExampleCriteria = userInfoExample.createCriteria();
        userInfoExampleCriteria.andUserNameLike(key);
        List<TUserInfo> tUserInfos = userInfoMapper.selectByExample(userInfoExample);
        List<Integer> userIds = new ArrayList<>();
        for (TUserInfo tUserInfo : tUserInfos) {
            Integer userId = tUserInfo.getUserId();
            userIds.add(userId);
        }

        // 添加文章标题条件
        TArticleExample articleExample = new TArticleExample();
        TArticleExample.Criteria criteria1 = articleExample.createCriteria();
        criteria1.andArtTitleLike(key);
        articleExample.or(criteria1);

        if (userIds.size() != 0) {
            // 添加用户条件
            TArticleExample.Criteria criteria2 = articleExample.createCriteria();
            criteria2.andArtUserIdIn(userIds);
            articleExample.or(criteria2);
        }

        List<TArticle> tArticles = articleMapper.selectByExampleWithBLOBs(articleExample);

        getUserInfo(tArticles.toArray(new TArticle[0]));
        getSection(tArticles.toArray(new TArticle[0]));

        return new PageInfo<TArticle>(tArticles);
    }

    @Override
    public List<TArticleType> selectArtTypeByArtId(Integer artId) {
        // 查询多对多表中的数据
        TArtTypeArtExample artTypeArtExample = new TArtTypeArtExample();
        TArtTypeArtExample.Criteria criteria = artTypeArtExample.createCriteria();
        criteria.andArtIdEqualTo(artId);
        List<TArtTypeArt> tArtTypeArts = artTypeArtMapper.selectByExample(artTypeArtExample);
        //根据多对多表中的数据, 查出文章一对多的数据
        List<TArticleType> tArticleTypes = new ArrayList<>();
        for (TArtTypeArt tArtTypeArt : tArtTypeArts) {
            Integer artTypeId = tArtTypeArt.getArtTypeId();
            TArticleType tArticleType = articleTypeMapper.selectByPrimaryKey(artTypeId);
            tArticleTypes.add(tArticleType);
        }
        return tArticleTypes;
    }

    @Override
    public int addArticle(TArticle article) {
        article.setCreate(new Date());

        List<String> typeNames = article.getTypeNames();
        List<Integer> typeIds = null;
        if (typeNames != null) { // 此时需要添加类型
            typeIds = new ArrayList<>();
            for (String typeName : typeNames) {
                // 查询数据库, 如果数据库中没有此类型的数据, 那么添加此类型, 如果有此类型, 直接将此类型添加到文章类型映射表中
                TArticleTypeExample articleTypeExample = new TArticleTypeExample();
                TArticleTypeExample.Criteria articleTypeExampleCriteria = articleTypeExample.createCriteria();
                articleTypeExampleCriteria.andTypeNameEqualTo(typeName);

                List<TArticleType> tArticleTypes = articleTypeMapper.selectByExample(articleTypeExample);
                Integer typeId;
                if (tArticleTypes != null && tArticleTypes.size() != 0) {
                    typeId = tArticleTypes.get(0).getTypeId();
                } else { // 将此类型添加到数据库中
                    TArticleType articleType = new TArticleType();
                    articleType.setTypeName(typeName);
                    articleType.setCreate(new Date());

                    articleTypeMapper.insertSelective(articleType);
                    typeId = articleType.getTypeId();
                }
                if (typeId != null) {
                    typeIds.add(typeId);
                }
            }
        }

        int i = articleMapper.insertSelective(article);
        Integer artId = article.getArtId();

        // 将新建的类型添加到文章类型映射表中
        if (typeIds != null) {
            for (Integer typeId : typeIds) {
                TArtTypeArt artTypeArt = new TArtTypeArt();
                artTypeArt.setArtId(artId);
                artTypeArt.setArtTypeId(typeId);

                artTypeArtMapper.insertSelective(artTypeArt);
            }
        }

        return i;
    }

    @Override
    public int modifyArticle(TArticle article) {
        // 修改文章之前查询是否有文章类型需要修改
        List<Integer> typeIds = article.getTypeIds();
        if (typeIds != null) {
            TArtTypeArtExample artTypeArtExample = new TArtTypeArtExample();
            TArtTypeArtExample.Criteria artTypeArtExampleCriteria = artTypeArtExample.createCriteria();
            artTypeArtExampleCriteria.andArtIdEqualTo(article.getArtId());
            artTypeArtExampleCriteria.andArtTypeIdIn(typeIds);

            // 删除掉本篇文章没有的类型
            artTypeArtMapper.deleteByExample(artTypeArtExample);
        }

        List<String> typeNames = article.getTypeNames();
        List<Integer> addTypeIds = null;
        if (typeNames != null) { // 此时需要添加类型
            addTypeIds = new ArrayList<>();
            for (String typeName : typeNames) {
                // 查询数据库, 如果数据库中没有此类型的数据, 那么添加此类型, 如果有此类型, 直接将此类型添加到文章类型映射表中
                TArticleTypeExample articleTypeExample = new TArticleTypeExample();
                TArticleTypeExample.Criteria articleTypeExampleCriteria = articleTypeExample.createCriteria();
                articleTypeExampleCriteria.andTypeNameEqualTo(typeName);

                List<TArticleType> tArticleTypes = articleTypeMapper.selectByExample(articleTypeExample);
                Integer typeId;
                if (tArticleTypes != null && tArticleTypes.size() != 0) {
                    typeId = tArticleTypes.get(0).getTypeId();
                } else { // 将此类型添加到数据库中
                    TArticleType articleType = new TArticleType();
                    articleType.setTypeName(typeName);
                    articleType.setCreate(new Date());

                    articleTypeMapper.insertSelective(articleType);
                    typeId = articleType.getTypeId();
                }
                if (typeId != null) {
                    addTypeIds.add(typeId);
                }
            }
        }

        Integer artId = article.getArtId();
        // 将新建的类型添加到文章类型映射表中
        if (addTypeIds != null) {
            for (Integer typeId : addTypeIds) {
                TArtTypeArt artTypeArt = new TArtTypeArt();
                artTypeArt.setArtId(artId);
                artTypeArt.setArtTypeId(typeId);

                artTypeArtMapper.insertSelective(artTypeArt);
            }
        }

        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public int deleteArticle(Integer artId) {

        // 首先删除此文章对应的类型信息映射
        TArtTypeArtExample artTypeArtExample = new TArtTypeArtExample();
        TArtTypeArtExample.Criteria artTypeArtExampleCriteria = artTypeArtExample.createCriteria();
        artTypeArtExampleCriteria.andArtIdEqualTo(artId);
        artTypeArtMapper.deleteByExample(artTypeArtExample);

        // 删除文章
        return articleMapper.deleteByPrimaryKey(artId);
    }

    @Override
    public PageInfo<TArticle> selectArticleByPageNumAndUserId(PageNum pageNum, Integer userId) {
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TArticleExample articleExample = new TArticleExample();
        TArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andArtUserIdEqualTo(userId);
        articleExample.setOrderByClause("`create` desc");
        List<TArticle> tArticles = articleMapper.selectByExample(articleExample);

        return new PageInfo<>(tArticles);
    }

    @Override
    public TArticle selectArticleByArtId(Integer artId) {
        TArticle article = articleMapper.selectByPrimaryKey(artId);

        // 每次查询热度加一
        article.setArtHotNum(article.getArtHotNum() + 1);
        articleMapper.updateByPrimaryKeySelective(article);

        getUserInfo(article);
        getSection(article);
        getCommentCount(article);

        return article;
    }

    @Override
    public PageInfo<TArticle> selectArticleByPageNumAndSecId(PageNum pageNum, Integer secId) {

        TArticleExample articleExample1 = new TArticleExample();
        TArticleExample.Criteria criteria = articleExample1.createCriteria();
        criteria.andArtSecIdEqualTo(secId);
        criteria.andIsTopEqualTo("1");
        articleExample1.setOrderByClause("`create` desc");
        List<TArticle> tArticles1 = articleMapper.selectByExample(articleExample1);

        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TArticleExample articleExample = new TArticleExample();
        TArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andArtSecIdEqualTo(secId);
        articleExample.setOrderByClause("`create` desc");
        List<TArticle> tArticles = articleMapper.selectByExample(articleExample);

        // hashset去重
        HashSet<TArticle> set = new HashSet<>(tArticles1);
        for (TArticle article : tArticles) {
            if (set.add(article)) {
                tArticles1.add(article);
            }
        }


        getUserInfo(tArticles1.toArray(new TArticle[0]));
        getCommentCount(tArticles1.toArray(new TArticle[0]));

        return new PageInfo<>(tArticles1);
    }

    /**
     * 查询文章有多少条一级评论
     *
     * @param tArticles 文章信息
     */
    private void getCommentCount(TArticle... tArticles) {
        for (TArticle tArticle : tArticles) {
            if (tArticle != null) {
                Integer artId = tArticle.getArtId();

                TCommentExample commentExample = new TCommentExample();
                TCommentExample.Criteria criteria = commentExample.createCriteria();
                criteria.andComArtIdEqualTo(artId);
                long i = commentMapper.countByExample(commentExample);
                tArticle.setCommentCount((int) i);
            }
        }
    }
}
