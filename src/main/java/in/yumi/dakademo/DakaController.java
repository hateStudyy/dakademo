package in.yumi.dakademo;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public String record(@RequestBody ExerciseRecord exerciseRecord, @RequestParam("userId") Integer userId) {
        if (exerciseRecord == null) {
            throw new RuntimeException("打卡数据不能为空！");
        }
        if (userId == null) {
            throw new RuntimeException("用户ID不能为空！");
        }
        boolean record = exerciseRecordService.record(exerciseRecord, userId);
        if (record) {
            return "打卡成功！";
        }

        return "打卡失败！今日已打卡";
    }

    @GetMapping("/list")
    public List<ExerciseRecord> listRecord() {
        List<ExerciseRecord> list = exerciseRecordService.getBaseMapper().selectList(null);
        System.out.println(list);
        ExerciseRecord exerciseRecord = new ExerciseRecord();
        Long id = exerciseRecord.getId();
        return list;
    }

    @GetMapping("/timezone")
    public String getTimeZone() {
        return "当前时区：" + java.util.TimeZone.getDefault().getID();
    }


    /**
     * 接入ai
     */
    @PostMapping("/ai")
    public String ai(@RequestBody String content) {

        return "ai: " + content;
    }
}
