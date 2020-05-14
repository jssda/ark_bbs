package pers.jssd.ark.rpc.service;

import com.github.pagehelper.PageInfo;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.rpc.pojo.TMessage;

import java.util.List;

/**
 * 消息服务类
 *
 * @author jssdjing@gmail.com
 */
public interface TMessageService {

    int insertMessage(TMessage message);

    /**
     * 查询用户未读消息
     */
    List<TMessage> selectMessageByUserId(Integer userId);

    /**
     * 删除一个信息
     */
    int deleteMessage(Integer mesId);

    PageInfo<TMessage> selectMessageByPageNum(PageNum pageNum);
}
