package guru.springframework.springreactivemongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${sfg.mogohost}")
    private String mongoHost;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "sfg";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.credential(MongoCredential
                        .createCredential("root", "admin", "password".toCharArray()))
                .applyToClusterSettings(settings ->
                        settings.hosts(
                                Collections.singletonList(new ServerAddress(mongoHost, 27017))));
    }
}
