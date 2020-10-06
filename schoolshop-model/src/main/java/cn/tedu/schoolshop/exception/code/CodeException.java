package cn.tedu.schoolshop.exception.code;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 16:25
 */
public class CodeException extends RuntimeException {
    public CodeException() {
        super();
    }

    public CodeException(String message) {
        super(message);
    }

    public CodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeException(Throwable cause) {
        super(cause);
    }

    protected CodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
