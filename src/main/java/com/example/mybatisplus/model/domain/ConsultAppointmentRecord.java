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
 * @author Beacon
 * @since 2021-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConsultAppointmentRecord对象", description="")
public class ConsultAppointmentRecord extends Model<ConsultAppointmentRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "consult_appoint_id", type = IdType.AUTO)
    private Long consultAppointId;

    private Long sId;

    private Integer tpId;

    private Long locationId;

    private Long cId;

    @TableLogic
    @TableField("is_deleted")
    private Boolean isDeleted;

    private LocalDate date;

    private Boolean isFinished;


    @Override
    protected Serializable pkVal() {
        return this.consultAppointId;
    }

}
