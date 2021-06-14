package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author Beacon
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Student对象", description="学生表")
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "s_id", type = IdType.AUTO)
    private Long sId;

    private String code;

    private String name;

    private String phone;

    private String college;

    private Integer grade;

    private String gender;

    private LocalDate birthDate;

    private Boolean isQualified;

    private String password;


    @Override
    protected Serializable pkVal() {
        return this.sId;
    }

}
