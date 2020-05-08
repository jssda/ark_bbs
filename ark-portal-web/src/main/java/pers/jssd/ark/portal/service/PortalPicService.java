package pers.jssd.ark.portal.service;

import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ark.beans.PicResult;

/**
 * 图片上传服务
 *
 * @author jssdjing@gmail.com
 */
public interface PortalPicService {

    /**
     * 上传一个图片
     *
     * @param file 上传的图片信息
     * @return 返回上成功与否的响应信息
     */
    PicResult uploadImage(MultipartFile file);

}
