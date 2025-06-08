package in.yumi.dakademo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import in.yumi.dakademo.mapper.ExerciseRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
* @author yumi
* @description 针对表【exercise_record】的数据库操作Service实现
* @createDate 2025-06-08 20:09:37
*/
@Service
public class ExerciseRecordServiceImpl extends ServiceImpl<ExerciseRecordMapper, ExerciseRecord>
    implements ExerciseRecordService{
    @Resource
    private ExerciseRecordMapper exerciseRecordMapper;

    @Override
    public boolean record(ExerciseRecord exerciseRecord, Integer userId) {
        LocalDate today = LocalDate.now();
        List<ExerciseRecord> records = this.query().like("create_time", today).list();
        if(!records.isEmpty()) {
            return false;
        }
        int insert = this.baseMapper.insert(exerciseRecord);
        return insert > 0;
    }
}




