package pers.jssd.ark.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.mapper.TArticleMapper;
import pers.jssd.ark.rpc.mapper.TSectionMapper;
import pers.jssd.ark.rpc.mapper.TUserInfoMapper;
import pers.jssd.ark.rpc.pojo.TArticleExample;
import pers.jssd.ark.rpc.pojo.TSection;
import pers.jssd.ark.rpc.pojo.TSectionExample;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.service.TSectionService;

import java.util.Date;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TSectionServiceImpl implements TSectionService {

    private final TSectionMapper sectionMapper;
    private final TUserInfoMapper userInfoMapper;
    private final TArticleMapper articleMapper;

    public TSectionServiceImpl(TSectionMapper sectionMapper, TUserInfoMapper userInfoMapper, TArticleMapper articleMapper) {
        this.sectionMapper = sectionMapper;
        this.userInfoMapper = userInfoMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public PageInfo<TSection> selectSectionByPageNum(PageNum pageNum) {
        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TSectionExample sectionExample = new TSectionExample();

        List<TSection> tSections = sectionMapper.selectByExample(sectionExample);
        getUserInfo(tSections.toArray(new TSection[0]));

        return new PageInfo<>(tSections);
    }

    @Override
    public PageInfo<TSection> selectSectionByPageNumAndKey(PageNum pageNum, String key) {
        key = "%" + key + "%";

        PageHelper.startPage(pageNum.getPage(), pageNum.getLimit());
        TSectionExample sectionExample = new TSectionExample();
        TSectionExample.Criteria sectionExampleCriteria = sectionExample.createCriteria();
        sectionExampleCriteria.andSecTitleLike(key);

        List<TSection> tSections = sectionMapper.selectByExample(sectionExample);
        getUserInfo(tSections.toArray(new TSection[0]));
        return new PageInfo<>(tSections);
    }

    @Override
    public TSection selectSectionBySecId(Integer secId) {
        return sectionMapper.selectByPrimaryKey(secId);
    }

    @Override
    public int deleteSection(Integer secId) {
        TArticleExample articleExample = new TArticleExample();
        TArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andArtSecIdEqualTo(secId);
        articleMapper.deleteByExample(articleExample);

        return sectionMapper.deleteByPrimaryKey(secId);
    }

    @Override
    public List<TSection> selectSectionByKey(String key) {
        key = "%" + key + "%";
        TSectionExample sectionExample = new TSectionExample();
        TSectionExample.Criteria sectionExampleCriteria = sectionExample.createCriteria();
        sectionExampleCriteria.andSecTitleLike(key);
        return sectionMapper.selectByExample(sectionExample);
    }

    @Override
    public int updateSection(TSection tSection) {
        return sectionMapper.updateByPrimaryKeySelective(tSection);
    }

    @Override
    public int deleteSections(List<Integer> secIds) {
        TArticleExample articleExample = new TArticleExample();
        TArticleExample.Criteria articleExampleCriteria = articleExample.createCriteria();
        articleExampleCriteria.andArtSecIdIn(secIds);
        articleMapper.deleteByExample(articleExample);

        TSectionExample sectionExample = new TSectionExample();
        TSectionExample.Criteria sectionExampleCriteria = sectionExample.createCriteria();
        sectionExampleCriteria.andSecIdIn(secIds);

        return sectionMapper.deleteByExample(sectionExample);
    }

    @Override
    public int addSection(TSection tSection) {
        tSection.setCreate(new Date());
        return sectionMapper.insertSelective(tSection);
    }

    /**
     * 取得版主信息
     *
     * @param tSections 需要取得版主信息的板块实体容器
     */
    private void getUserInfo(TSection... tSections) {
        for (TSection tSection : tSections) {
            if (tSection != null) {
                Integer secUserId = tSection.getSecUserId();
                TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(secUserId);
                tSection.setUser(tUserInfo);
            }
        }
    }
}
