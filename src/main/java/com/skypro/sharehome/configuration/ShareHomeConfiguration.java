package com.skypro.sharehome.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ShareHomeConfiguration {

    @Value("${telegram.bot.token}")
    private String token;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

    @Bean
    @Qualifier("catDataSource")
    @Primary
    @ConfigurationProperties(prefix="cat.datasource")
    DataSource catDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("dogDataSource")
    @ConfigurationProperties(prefix="dog.datasource")
    DataSource dogDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("catJdbcTemplate")
    JdbcTemplate catJdbcTemplate(@Qualifier("catDataSource")DataSource catDataSource) {
        return new JdbcTemplate(catDataSource);
    }

    @Bean
    @Qualifier("dogJdbcTemplate")
    JdbcTemplate dogJdbcTemplate(@Qualifier("dogDataSource")DataSource dogDataSource) {
        return new JdbcTemplate(dogDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "cat.datasource.liquibase")
    public LiquibaseProperties catLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase catLiquibase() {
        return springLiquibase(catDataSource(), catLiquibaseProperties());
    }

    @Bean
    @ConfigurationProperties(prefix = "dog.datasource.liquibase")
    public LiquibaseProperties dogLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase dogLiquibase() {
        return springLiquibase(dogDataSource(), dogLiquibaseProperties());
    }

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}
