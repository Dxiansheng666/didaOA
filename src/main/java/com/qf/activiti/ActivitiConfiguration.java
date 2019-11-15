package com.qf.activiti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author FJM
 * @create 2019/11/9
 * @Time 15:09
 */
@Configuration
public class ActivitiConfiguration {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(platformTransactionManager);
        spec.setDatabaseSchemaUpdate("true");
        Resource[] resources = new Resource[0];
        //启动自动部署流程
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:processes/*/bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        spec.setDeploymentResources(resources);
        return spec;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactory() {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        //processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }

//    @Bean
//    public RepositoryService repositoryService() {
//        try {
//            return processEngineFactory().getObject().getRepositoryService();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Bean
    public RuntimeService runtimeService() {
        try {
            return processEngineFactory().getObject().getRuntimeService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public TaskService taskService() {
        try {
            return processEngineFactory().getObject().getTaskService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public HistoryService historyService() {
        try {
            return processEngineFactory().getObject().getHistoryService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
