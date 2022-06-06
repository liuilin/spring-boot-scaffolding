package config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuqiang
 * @Description TODO
 * @Date 2020/12/27 0027
 */
@Configuration
@MapperScan("daos")
public class MyBatisConfig {

}
