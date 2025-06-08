package in.yumi.dakademo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daka")
public class DakaController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/execute")
    public String daka() {
        return "打卡成功！";
    }

    @GetMapping("/list")
    public String listRecord() {
        return "listRecord！";
    }

}
