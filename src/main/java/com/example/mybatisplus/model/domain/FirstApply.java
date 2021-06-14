package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FirstApply对象", description="")
public class FirstApply extends Model<FirstApply> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fa_id", type = IdType.AUTO)
    private Long faId;

    private Long sId;

    private Integer tpId;

    private Integer score;

    private String name;

    private String phone;

    private String address;

    private String emergencyPhone;

    private String physicalIllness;

    private Boolean isDiagnosed;

    private String emergencyLevel;

    private String problemType;

    private String consultExpectation;

    private String consultHistory;

    private Boolean isFinished;


    @Override
    protected Serializable pkVal() {
        return this.faId;
    }

}
