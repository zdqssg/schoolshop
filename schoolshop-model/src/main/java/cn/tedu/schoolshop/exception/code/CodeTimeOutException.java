package cn.tedu.schoolshop.exception.code;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 16:36
 */
public class CodeTimeOutException  extends CodeException{
    public CodeTimeOutException() {
        super();
    }

    public CodeTimeOutException(String message) {
        super(message);
    }

    public CodeTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeTimeOutException(Throwable cause) {
        super(cause);
    }

    protected CodeTimeOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
