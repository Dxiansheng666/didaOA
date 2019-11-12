package com.qf.mapper;
import com.qf.pojo.WeekReport;

import java.util.List;


/**
 * @author FJM
 * @create 2019/11/11
 * @Time 20:07
 */
public interface WeekReportMapper {
    //通过sid删除周报
    public int deleteWeekReportBySid(int sid);
    //通过sid获取周报
    List<WeekReport> getWeekReportListByUid(int uid);

}
