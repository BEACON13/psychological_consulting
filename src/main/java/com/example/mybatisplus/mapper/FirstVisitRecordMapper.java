package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.FirstVisitRecordVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kristy
 * @since 2021-06-13
 */
@Repository
public interface FirstVisitRecordMapper extends BaseMapper<FirstVisitRecord> {
    List<FirstVisitRecordVO> showRecords(@Param("id") Long id);

    List<FirstVisitRecordVO> showAllRecords(@Param("id") Long id);

    List<FirstVisitRecordVO> getRecordsByName(@Param("id") Long id, @Param("stuName") String stuName);

    List<FirstVisitRecordVO> getFVRecord(@Param("id") Long id);

    List<FirstVisitRecordVO> getAllRecordAdmin();
}
