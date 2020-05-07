package pers.jssd.ark.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Ftp文件上传工具类
 *
 * @author jssdjing@gmail.com
 */
public class FtpUtil {

    /**
     * 上传图片类型的文件
     *
     * @param ftpHost     ftp主机地址
     * @param ftpPort     ftp上传文件端口
     * @param ftpUserName ftp上传文件用户名
     * @param ftpPassword ftp密码
     * @param workDir     上传到哪个目录
     * @param remote      上传之后远程文件名
     * @param inputStream 文件流对象
     * @return true则上传成功, false则上传失败
     */
    public static boolean picUpload(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword, String workDir, String remote, InputStream inputStream) {
        boolean flag = false;
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUserName, ftpPassword);

            if (!ftpClient.changeWorkingDirectory(workDir)) {
                if (ftpClient.makeDirectory(workDir)) {
                    ftpClient.changeWorkingDirectory(workDir);
                }
            }
            // 设置文件上传类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile(remote, inputStream);

            inputStream.close();

            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                if (ftpClient != null) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}
