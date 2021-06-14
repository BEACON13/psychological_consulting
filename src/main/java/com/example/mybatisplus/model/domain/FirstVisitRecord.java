package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 
 * </p>
 *
 * @author Kristy
 * @since 2021-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FirstVisitRecord对象", description="")
public class FirstVisitRecord extends Model<FirstVisitRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fvr_id", type = IdType.AUTO)
    private Long fvrId;

    private Long sId;

    private Integer tpId;

    private Long locationId;

    private Long fvId;

    private LocalDate date;

    /**
     * 逻辑删除属性
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    private Boolean isFinished;


    @Override
    protected Serializable pkVal() {
        return this.fvrId;
    }

}
