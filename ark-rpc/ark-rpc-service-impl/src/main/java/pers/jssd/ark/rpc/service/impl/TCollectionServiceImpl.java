package pers.jssd.ark.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.mapper.TArticleMapper;
import pers.jssd.ark.rpc.mapper.TCollectionMapper;
import pers.jssd.ark.rpc.mapper.TUserInfoMapper;
import pers.jssd.ark.rpc.pojo.TArticle;
import pers.jssd.ark.rpc.pojo.TCollection;
import pers.jssd.ark.rpc.pojo.TCollectionExample;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.service.TCollectionService;

import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TCollectionServiceImpl implements TCollectionService {

    private final TCollectionMapper collectionMapper;
    private final TUserInfoMapper userInfoMapper;
    private final TArticleMapper articleMapper;

    public TCollectionServiceImpl(TCollectionMapper collectionMapper, TUserInfoMapper userInfoMapper, TArticleMapper articleMapper) {
        this.collectionMapper = collectionMapper;
        this.userInfoMapper = userInfoMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public PageInfo<TCollection> selectCollectionByPageNum(PageNum pageNum) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TCollectionExample collectionExample = new TCollectionExample();
        List<TCollection> tCollections = collectionMapper.selectByExample(collectionExample);

        getUserInfo(tCollections.toArray(new TCollection[0]));
        getArticle(tCollections.toArray(new TCollection[0]));

        return new PageInfo<>(tCollections);
    }

    @Override
    public PageInfo<TCollection> selectCollectionByPageNumAndKey(PageNum pageNum, Integer colUserId) {
        // 开始分页
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TCollectionExample collectionExample = new TCollectionExample();
        TCollectionExample.Criteria criteria = collectionExample.createCriteria();
        criteria.andColUserIdEqualTo(colUserId);
        List<TCollection> tCollections = collectionMapper.selectByExample(collectionExample);

        getUserInfo(tCollections.toArray(new TCollection[0]));
        getArticle(tCollections.toArray(new TCollection[0]));

        return new PageInfo<>(tCollections);
    }

    @Override
    public int deleteCollection(Integer colId) {
        return collectionMapper.deleteByPrimaryKey(colId);
    }

    @Override
    public int deleteCollections(List<Integer> colIds) {
        TCollectionExample collectionExample = new TCollectionExample();
        TCollectionExample.Criteria collectionExampleCriteria = collectionExample.createCriteria();
        collectionExampleCriteria.andColIdIn(colIds);
        return collectionMapper.deleteByExample(collectionExample);
    }

    @Override
    public int insertCollection(TCollection tCollection) {
        tCollection.setCreate(new Date());
        return collectionMapper.insertSelective(tCollection);
    }

    @Override
    public List<TCollection> selectCollectionByArtIdAndUserId(TCollection collection) {
        TCollectionExample collectionExample = new TCollectionExample();
        TCollectionExample.Criteria collectionExampleCriteria = collectionExample.createCriteria();
        collectionExampleCriteria.andColArtIdEqualTo(collection.getColArtId());
        collectionExampleCriteria.andColUserIdEqualTo(collection.getColUserId());

        return collectionMapper.selectByExample(collectionExample);
    }

    /**
     * 获取收藏表中的文章信息
     *
     * @param collections 返回获取到的文章信息
     */
    private void getArticle(TCollection... collections) {
        for (TCollection collection : collections) {
            if (collection != null) {
                Integer colArtId = collection.getColArtId();
                TArticle article = articleMapper.selectByPrimaryKey(colArtId);
                collection.setArticle(article);
            }
        }
    }

    /**
     * 查询收藏表中的用户信息
     *
     * @param collections 返回查询到的带用户信息的收藏信息
     */
    private void getUserInfo(TCollection... collections) {
        for (TCollection collection : collections) {
            if (collection != null) {
                Integer colUserId = collection.getColUserId();
                TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(colUserId);
                collection.setUser(tUserInfo);
            }
        }
    }
}
