package cn.tedu.schoolshop.exception.file;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 文件上传大小错误}
 * @email zdq247209@163.com
 * @date 2020/9/21 14:11
 */
public class FileSizeException extends FileUploadException{
    public FileSizeException() {
        super();
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    protected FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
