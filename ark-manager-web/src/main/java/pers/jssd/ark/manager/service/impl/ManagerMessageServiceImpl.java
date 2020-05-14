package pers.jssd.ark.manager.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pers.jssd.ark.beans.PageNum;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerMessageService;
import pers.jssd.ark.rpc.pojo.TMessage;
import pers.jssd.ark.rpc.service.TMessageService;
import pers.jssd.ark.util.PageUtil;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerMessageServiceImpl implements ManagerMessageService {

    private final TMessageService messageService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ManagerMessageServiceImpl(TMessageService messageService) {this.messageService = messageService;}

    @Override
    public TableResult listMessage(Integer page, Integer limit) {
        TableResult tableResult = null;
        PageNum pageNum = PageUtil.getPageNum(page, limit);
        PageInfo<TMessage> messagePageInfo = messageService.selectMessageByPageNum(pageNum);
        if (messagePageInfo == null || messagePageInfo.getSize() == 0) {
            tableResult = new TableResult();
            tableResult.setCount(-1);
            tableResult.setMsg("查询失败");
        } else {
            tableResult = new TableResult();
            tableResult.setCode(0);
            tableResult.setCount((int) messagePageInfo.getTotal());
            tableResult.setData(messagePageInfo.getList());
            tableResult.setMsg("查询成功");
        }
        return tableResult;
    }
}
