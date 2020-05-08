package pers.jssd.ark.rpc.service.impl;

import org.springframework.stereotype.Service;
import pers.jssd.ark.rpc.mapper.TMessageMapper;
import pers.jssd.ark.rpc.pojo.TMessage;
import pers.jssd.ark.rpc.service.TMessageService;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class TMessageServiceImpl implements TMessageService {

    private final TMessageMapper messageMapper;

    public TMessageServiceImpl(TMessageMapper messageMapper) {this.messageMapper = messageMapper;}

    @Override
    public int insertMessage(TMessage message) {
        return messageMapper.insertSelective(message);
    }
}
