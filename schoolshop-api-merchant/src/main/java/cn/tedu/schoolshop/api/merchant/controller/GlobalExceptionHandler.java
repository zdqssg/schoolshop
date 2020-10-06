package cn.tedu.schoolshop.api.merchant.controller;

import cn.tedu.schoolshop.exception.file.*;
import cn.tedu.schoolshop.exception.service.*;
import cn.tedu.schoolshop.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局处理异常的类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
//123
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public R handleException(Throwable e) {
        if (e instanceof NickNameDuplicateException) {
            return R.failure(R.State.ERR_NICKNAME_DUPLICATE, e);
        } else if (e instanceof PhoneDuplicateException) {
            return R.failure(R.State.ERR_PHONE_DUPLICATE, e);
        } else if (e instanceof InsertException) {
            return R.failure(R.State.ERR_INSERT, e);
        } else if (e instanceof FileSizeException) {
            return R.failure(R.State.ERR_FILE_SIZE, e);
        } else if (e instanceof FileEmptyException) {
            return R.failure(R.State.ERR_FILE_EMPTY, e);
        } else if (e instanceof FileTypeException) {
            return R.failure(R.State.ERR_FILE_TYPE, e);
        } else if (e instanceof FileUploadIOException) {
            return R.failure(R.State.ERR_FILE_IO, e);
        }  else if (e instanceof FileStateException) {
            return R.failure(R.State.ERR_FILE_STATE, e);
        } else if (e instanceof IllegalParameterException) {
            return R.failure(R.State.ERR_ILLEGAL_PARAMETER, e);
        } else {
            return R.failure(R.State.ERR_UNKNOWN, e);
        }
    }

}
