package pers.jssd.ark.manager.service;

import pers.jssd.ark.beans.TableResult;

/**
 * @author jssdjing@gmail.com
 */
public interface ManagerMessageService {
    /**
     * 展示信息
     */
    TableResult listMessage(Integer page, Integer limit);
}
