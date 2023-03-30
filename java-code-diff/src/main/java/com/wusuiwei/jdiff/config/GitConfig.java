package com.wusuiwei.jdiff.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class GitConfig {
    @Value(value = "${git.username}")
    private String username;
    @Value(value = "${git.password}")
    private String password;
    @Value(value = "${git.sshKey}")
    private String sshKey;
    @Value(value = "${git.baseDir}")
    private String baseDir;

}
