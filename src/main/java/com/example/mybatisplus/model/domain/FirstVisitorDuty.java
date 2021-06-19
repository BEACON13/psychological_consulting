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
 * @author Kristy
 * @since 2021-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FirstVisitorDuty对象", description="")
public class FirstVisitorDuty extends Model<FirstVisitorDuty> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fvd_id", type = IdType.AUTO)
    private Long fvdId;

    private Integer tpId;

    private Long locationId;

    private Long fvId;

    private Boolean isAvailable;


    @Override
    protected Serializable pkVal() {
        return this.fvdId;
    }

}
