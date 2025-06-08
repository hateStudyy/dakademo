package in.yumi.dakademo;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daka")
public class DakaController {
    @Resource
    private ExerciseRecordService exerciseRecordService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/execute")
    public String daka() {
        return "打卡成功！";
    }

    @GetMapping("/list")
    public List<ExerciseRecord> listRecord() {
        List<ExerciseRecord> list = exerciseRecordService.getBaseMapper().selectList(null);
        System.out.println(list);
        ExerciseRecord exerciseRecord = new ExerciseRecord();
        Long id = exerciseRecord.getId();
        return list;
    }

}
