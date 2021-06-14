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
@ApiModel(value="ClosingReport对象", description="")
public class ClosingReport extends Model<ClosingReport> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "closing_report_id", type = IdType.AUTO)
    private Long closingReportId;

    private Long sId;

    private Long cId;

    private String problemType;

    private Integer consultNum;

    private String consultEffectSelf;


    @Override
    protected Serializable pkVal() {
        return this.closingReportId;
    }

}
