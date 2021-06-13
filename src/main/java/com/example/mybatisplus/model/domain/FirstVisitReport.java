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
 * @since 2021-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FirstVisitReport对象", description="")
public class FirstVisitReport extends Model<FirstVisitReport> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fvreport_id", type = IdType.AUTO)
    private Long fvreportId;

    private Long sId;

    private Integer tpId;

    private Long fvId;

    private String dangerLevel;

    private String problemType;

    private String conclusion;

    private LocalDate date;


    @Override
    protected Serializable pkVal() {
        return this.fvreportId;
    }

}
