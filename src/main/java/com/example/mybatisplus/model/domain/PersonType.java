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
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PersonType对象", description="")
public class PersonType extends Model<PersonType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pt_id", type = IdType.AUTO)
    private Long ptId;

    private Long pId;

    private String type;


    @Override
    protected Serializable pkVal() {
        return this.ptId;
    }

}
