package in.yumi.dakademo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("in.yumi.dakademo.mapper")
public class DakademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DakademoApplication.class, args);
    }

}
