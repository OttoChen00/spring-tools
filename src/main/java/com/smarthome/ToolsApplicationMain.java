package com.smarthome;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = { "com.**" })
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)})
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ToolsApplicationMain
{
    public static void main( String[] args ) {
        SpringApplication.run(ToolsApplicationMain.class,args);
    }
}
