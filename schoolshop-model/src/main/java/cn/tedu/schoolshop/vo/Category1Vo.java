package cn.tedu.schoolshop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 店铺分类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Data

public class Category1Vo implements Serializable {

    private Integer id;

    private String name;


}
