package pers.jssd.ark.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jssd.ark.beans.TableResult;
import pers.jssd.ark.manager.service.ManagerMessageService;

/**
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/manager/message")
public class MessageController {

    private final ManagerMessageService messageService;

    public MessageController(ManagerMessageService messageService) {this.messageService = messageService;}

    /**
     * 展示信息
     */
    @RequestMapping("listMessage")
    public TableResult listMessage(@RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        return messageService.listMessage(page, limit);
    }

}
