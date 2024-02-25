package ru.t1academy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfigProperties {
    private String topicName;
    private Boolean enabled;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Boolean getIsEnabled() {
        return enabled;
    }

    public KafkaConfigProperties setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
