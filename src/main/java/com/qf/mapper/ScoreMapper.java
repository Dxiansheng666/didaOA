package com.qf.mapper;

import com.qf.pojo.Score;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 20:03
 */
public interface ScoreMapper {
    //通过sid删除成绩表
    public int deleteScoreBySid(int sid);

    //删除学生之前先查询该学生是否有分数
    public List<Score> getScoreListByUid(int sid);
}
