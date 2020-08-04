package com.bbaker.book.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoggingTestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/test")
    public ModelAndView test() throws Exception{
        logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");

        return null;
    }

    @GetMapping("/test/index")
    public String index(Model model) {
        return "/admin/test";
    }

    @GetMapping("/test/index2")
    public String index2() {
        return "/test2";
    }

    @GetMapping("/test/index3")
    public String index3() {
        return "test2";
    }

}
