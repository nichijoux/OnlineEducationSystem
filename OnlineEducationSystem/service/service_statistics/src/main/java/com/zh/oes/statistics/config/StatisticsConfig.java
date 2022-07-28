package com.zh.oes.statistics.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zh.oes.statistics.mapper")
@ComponentScan(basePackages = {"com.zh.oes"})
public class StatisticsConfig {
}
