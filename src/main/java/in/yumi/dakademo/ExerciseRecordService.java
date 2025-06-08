package in.yumi.dakademo;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yumi
* @description 针对表【exercise_record】的数据库操作Service
* @createDate 2025-06-08 20:09:37
*/
public interface ExerciseRecordService extends IService<ExerciseRecord> {
    boolean record(ExerciseRecord exerciseRecord, Integer userId);
}
