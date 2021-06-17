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
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AddConsult对象", description="")
public class AddConsult extends Model<AddConsult> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "add_c_id", type = IdType.AUTO)
    private Long addCId;

    private Long sId;

    private Long cId;

    private Integer tpId;

    private Integer times;

    private Boolean isFinished;


    @Override
    protected Serializable pkVal() {
        return this.addCId;
    }

}
