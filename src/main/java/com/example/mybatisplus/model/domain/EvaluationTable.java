package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2022-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "EvaluationTable对象", description = "")
public class EvaluationTable extends Model<EvaluationTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评测表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评测表名称")
    private String evaluationName;

    @ApiModelProperty(value = "评测表描述")
    private String description;

    /**
     * 逻辑删除属性
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
