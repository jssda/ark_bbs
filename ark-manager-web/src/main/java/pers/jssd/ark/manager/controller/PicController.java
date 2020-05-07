package pers.jssd.ark.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ark.beans.PicResult;
import pers.jssd.ark.manager.service.ManagerPicService;

/**
 * 文件上传
 *
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/manager/pic")
public class PicController {

    private final ManagerPicService picService;

    public PicController(ManagerPicService picService) {this.picService = picService;}

    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public PicResult picUpload(@RequestParam("file") MultipartFile uploadFile) {
        return picService.uploadImage(uploadFile);
    }

}
