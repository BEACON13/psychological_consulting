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
@ApiModel(value="ConsultApply对象", description="")
public class ConsultApply extends Model<ConsultApply> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "consult_apply_id", type = IdType.AUTO)
    private Long consultApplyId;

    private Long sId;

    private String stuName;

    private String phone;

    private String address;

    private String emergencyPhone;

    private String dangerLevel;

    private String problemType;

    private Integer tpId1;

    private Integer tpId2;

    private Integer tpId3;

    private Integer num;

    private Boolean isFinished;


    @Override
    protected Serializable pkVal() {
        return this.consultApplyId;
    }

}
