package com.liumulin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * aliyun oss 的 yml 配置参数
*/
@Data
@Component
@ConfigurationProperties(prefix="isys.oss.aliyun-oss")
public class AliyunOssYmlConfig {
	private String endpoint;
	private String publicBucketName;
	private String privateBucketName;
	private String accessKeyId;
	private String accessKeySecret;
}



