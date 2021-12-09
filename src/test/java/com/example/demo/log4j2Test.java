package com.example.demo;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.LogManager;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest(classes = DemoApplication.class)
public class log4j2Test {
   private static Logger logger = LogManager.getLogger(log4j2Test.class);

    @Test
    public static void main(String[] args) {

        System.out.println(123);
            logger.trace("log4j的trace等级日志输出");
        System.out.println(13);
            logger.debug("log4j的debug等级日志输出");
        System.out.println(23);
            logger.info("log4j的info等级日志输出");
        System.out.println(3);
            logger.error("log4j的error等级日志输出");
        System.out.println(4);
            logger.fatal("log4j的fatal等级日志输出");
    }


}
