package pers.jssd.ark.manager.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ark.beans.PicResult;
import pers.jssd.ark.entity.Picture;
import pers.jssd.ark.manager.service.ManagerPicService;
import pers.jssd.ark.util.FtpUtil;
import pers.jssd.ark.util.IDUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerPicServiceImpl implements ManagerPicService {

    @Value("${FTP_HOST}")
    private String ftpHost;

    @Value("${FTP_PORT}")
    private Integer ftpPort;

    @Value("${FTP_USERNAME}")
    private String ftpUserName;

    @Value("${FTP_PASSWORD}")
    private String ftpPassword;

    @Value("${WORK_DIR}")
    private String workDir;

    @Value("${IMAGE_HTTP_PATH}")
    private String imageHttpPath;

    @Override
    public PicResult uploadImage(MultipartFile file) {
        boolean flag = false;
        String fileName = IDUtils.genImageName();
        String originalFilename = null;
        try {
            InputStream inputStream = file.getInputStream();
            originalFilename = file.getOriginalFilename();
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = fileName + extensionName;

            flag = FtpUtil.picUpload(ftpHost, ftpPort, ftpUserName, ftpPassword, workDir, fileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PicResult result = new PicResult();
        if (flag) {
            result.setCode(0);
            result.setMsg("图片上成功");
            result.setData(new Picture(imageHttpPath + "/" + fileName, originalFilename));
        } else {
            result.setCode(-1);
            result.setMsg("图片上失败");
        }

        return result;
    }
}
