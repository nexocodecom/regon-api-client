package com.nexocode.bir.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegonEnv {
    @Value("${regon-api.url}") String url;
    @Value("${regon-api.key}") String key;
}
