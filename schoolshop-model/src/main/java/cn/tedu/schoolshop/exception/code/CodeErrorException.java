package cn.tedu.schoolshop.exception.code;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 16:26
 */
public class CodeErrorException extends CodeException{
    public CodeErrorException() {
        super();
    }

    public CodeErrorException(String message) {
        super(message);
    }

    public CodeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeErrorException(Throwable cause) {
        super(cause);
    }

    protected CodeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
