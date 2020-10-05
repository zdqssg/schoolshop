package cn.tedu.schoolshop.getway.controller.RestController;

import cn.tedu.schoolshop.exception.file.FileSizeException;
import cn.tedu.schoolshop.exception.file.FileStateException;
import cn.tedu.schoolshop.exception.file.FileTypeException;
import cn.tedu.schoolshop.exception.file.FileUploadIOException;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.User;
import cn.tedu.schoolshop.security.LoginUserInfo;
import cn.tedu.schoolshop.util.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 19:27
 */
@RestController
@CrossOrigin
@RequestMapping("/img")
public class FileController {
    @Value("${project.fileupload.all-image.max-size}")
    long maxSize;
    @Value("${project.fileupload.all-image.content-types}")
    List<String> contentTypes;
    @Value("${project.fileupload.base-dir}")
    String baseDir;
    @Value("${project.resource-server-name}")
    String resourceServerName;

    /**
     * 单张上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R changeHeaderPhoto(MultipartFile file) {
        // 检查上传的图片是否为空
        if (file==null||file.isEmpty()){
            throw new FileSizeException("上传失败！请选择有效的文件");
        }
        // 检查上传的图片大小是否超标
        if (file.getSize()>maxSize){
            throw new FileSizeException("上传失败！不允许上传超过"+maxSize/1024/1024+"MB的图片");
        }
        // 检查上传的图片类型是否超标
        if (!contentTypes.contains(file.getContentType())){
            throw new FileTypeException("");
        }
        // 确定上传的文件保存到哪个文件夹
        String yearAndMonth= DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now());
        File parent = new File(baseDir, yearAndMonth);
        if (!parent.exists()){
            parent.mkdirs();
        }
        // 确定上传的文件保存时使用的文件名
        String filename = System.currentTimeMillis() + "-" + System.nanoTime();
        // String filename = UUID.randomUUID().toString();
        // 确定上传的文件保存时使用的扩展名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 确定上传的文件保存时使用的文件全名
        String child = filename + suffix;
        // 确定上传的文件保存到哪里
        File dest = new File(parent, child);
        try {
            // -- 执行保存上传的文件
            file.transferTo(dest);
        } catch (IOException e) {
            // -- 抛出FileUploadIOException
            throw new FileUploadIOException("上传失败！文件读写错误");
        } catch (IllegalStateException e) {
            // -- 抛出FileStateException
            throw new FileStateException("上传失败！文件参数错误");
        }
        String imageUrl=resourceServerName+"/"+yearAndMonth+"/"+child;
        return R.ok(imageUrl);

    }
}
