package huice.accompaniment.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Doge2077 2024/7/17
 */
@RestController
@RequestMapping("/order")
public class TestController {

    /**
     * 测试
     * @return test
     */
    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
