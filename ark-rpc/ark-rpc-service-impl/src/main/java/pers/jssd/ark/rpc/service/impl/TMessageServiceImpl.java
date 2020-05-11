package pers.jssd.ark.rpc.service.impl;

import org.springframework.stereotype.Service;
import pers.jssd.ark.rpc.mapper.TMessageMapper;
import pers.jssd.ark.rpc.mapper.TUserInfoMapper;
import pers.jssd.ark.rpc.pojo.TMessage;
import pers.jssd.ark.rpc.pojo.TUserInfo;
import pers.jssd.ark.rpc.service.TMessageService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TMessageServiceImpl implements TMessageService {

    private final TMessageMapper messageMapper;
    private final TUserInfoMapper userInfoMapper;

    public TMessageServiceImpl(TMessageMapper messageMapper, TUserInfoMapper userInfoMapper) {
        this.messageMapper = messageMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public int insertMessage(TMessage message) {
        return messageMapper.insertSelective(message);
    }

    @Override
    public List<TMessage> selectMessageByUserId(Integer userId) {
        List<TMessage> messages = messageMapper.selectMessageByUserId(userId);

        getFromUser(messages.toArray(new TMessage[0]));
        getToUser(messages.toArray(new TMessage[0]));

        return messages;
    }

    @Override
    public int deleteMessage(Integer mesId) {
        return messageMapper.deleteByPrimaryKey(mesId);
    }

    /**
     * 取得来自于哪个用户的信息
     */
    private void getFromUser(TMessage... messages) {
        for (TMessage message : messages) {
            if (message != null) {
                Integer fromUserId = message.getFromUserId();
                TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(fromUserId);
                message.setFromUser(userInfo);
            }
        }
    }

    /**
     * 取得发给哪个用户的信息
     */
    private void getToUser(TMessage... messages) {
        for (TMessage message : messages) {
            if (message != null) {
                Integer toUserId = message.getToUserId();
                TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(toUserId);
                message.setToUser(userInfo);
            }
        }
    }
}
