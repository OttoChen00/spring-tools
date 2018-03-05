package com.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = { "com.**" })
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)})
public class ToolsApplicationMain
{
    public static void main( String[] args ) {
        SpringApplication.run(ToolsApplicationMain.class,args);
    }
}
