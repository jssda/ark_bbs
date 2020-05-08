package pers.jssd.ark.portal.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ark.beans.PicResult;
import pers.jssd.ark.portal.service.PortalPicService;

/**
 * 文件上传
 *
 * @author jssdjing@gmail.com
 */
@RestController
@RequestMapping("/portal/pic")
public class PicController {

    private final PortalPicService picService;

    public PicController(PortalPicService picService) {this.picService = picService;}

    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PicResult picUpload(@RequestParam("file") MultipartFile uploadFile) {
        return picService.uploadImage(uploadFile);
    }

}
