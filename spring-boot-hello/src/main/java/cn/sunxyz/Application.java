package cn.sunxyz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Application {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

/*        List<String> list1 = Arrays.asList("7", "8", "9");
        List<String> list2 = Arrays.asList("7sfsdfsd", "8", "9");
        list.add(list1);
        list.add(list2);
        list.stream().flatMap(strings -> strings.stream()).forEach(System.out::println);*/
    }
}

