package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
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
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TimePeriod对象", description="")
public class TimePeriod extends Model<TimePeriod> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tp_id", type = IdType.AUTO)
    private Integer tpId;

    private LocalTime startTime;

    private Integer duration;

    private Integer weekday;

    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.tpId;
    }

}
