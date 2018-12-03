package com.eci.cosw.project.quicklyshop.security;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {

        // Set credentials
        MongoCredential credential = MongoCredential.createCredential("admin", "eci-cosw-project0", "averysecretandsecurepassword123".toCharArray());
        ServerAddress serverAddress = new ServerAddress("ds121163.mlab.com", 21163);

        // Mongo Client
        MongoClient mongoClient = new MongoClient(serverAddress, credential, new MongoClientOptions.Builder().build());


        return new SimpleMongoDbFactory(mongoClient, "eci-cosw-project0");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        MongoTemplate mongoTemplate = mongoTemplate();
        return new GridFsTemplate(mongoDbFactory(), mongoTemplate.getConverter());
    }


}
