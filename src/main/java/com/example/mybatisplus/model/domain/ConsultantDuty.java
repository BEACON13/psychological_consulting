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
 * 
 * </p>
 *
 * @author Kristy
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConsultantDuty对象", description="")
public class ConsultantDuty extends Model<ConsultantDuty> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cd_id", type = IdType.AUTO)
    private Long cdId;

    private Integer tpId;

    private Long locationId;

    private Long cId;

    private LocalDate freeTime;


    @Override
    protected Serializable pkVal() {
        return this.cdId;
    }

}
