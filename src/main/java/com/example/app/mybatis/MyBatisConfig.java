package com.example.app.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
스프링 컨테이너에 빈을 등록할 때 사용하는 어노테이션은 @Component이다.
그런데 빈 등록 어노테이션이 하나만 있는게 아니다.
여러가지가 있는데 그 중 하나가 @Configuration이다.
특별한 기능이 있는게 아니라 빈의 사용 목적을 구분짓는다.
즉, @Configuration은 설정을 위한 빈이라고 알려주고 등록하는 것이다.
 */
@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
//    ApplicationContext 는 스프링 부트의 핵심 인터페이스이다.
//    우리가 스프링 컨테이너 라고 부르는 논리적인 구조를 구현한 객체이다.(즉, 얘가 스프링 컨테이너)
//    Bean관리, DI 등을 담당한다.
//    이 객체는 우리 프로젝트의 전반적인 구성이나 설정을 알고 있기 때문에 도움을 받을 수 있다.
    private final ApplicationContext applicationContext;

//    이 어노테이션은 외부파일의 설정 값들을 자바 객체로 가져올 때 사용한다.
//    스프링부트에서는 application.properties라는 파일에 설정값을 전부 작성하기 때문에
//    해당 파일에서 특정 속성을 가져올 때 사용하는 어노테이션이다.
//    prefix는 접두사를 설정할 때 사용하며 "spring.datasource.hikari"로 시작하는 설정값을 전부 가져오라는
//    의미이다.
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
//        Hikari란?
//        HikariCP(히카리 커넥션 풀) 라이브러리를 의미한다.
//        빠르고, 가볍고, 설정이 쉽고, 안정성이 높다는 장점이 있다.
//        JSP에서 사용한 DBCP는 아파치톰캣에서 지원하는 라이브러리였으나
//        Spring에서는 Hikari를 사용한다. 성능이 상대적으로 더 좋아 MyBatis와 함께 많이 사용한다.
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
//        DataSource란?
//        DataSource 객체는 CP를 관리하고 Cp에 있는 커넥션 객체를 제공해주고 반납받는 등의 일을 한다.
//        모든 CP라이브러리는 DataSource객체를 사용한다.
//        이 객체가 DB의 커넥션을 관리하고, 커넥션 풀을 통해 커넥션 객체를 재사용한다.
//        정리하자면 CP를 사용하려면 DataSource객체가 필요하며 DataSource객체를 만들기 위해서는 DB정보가 필요하다.
//        해당 정보는 HikariConfig가 가지고 있다.
        return new HikariDataSource(hikariConfig());
    }

//    @Bean 어노테이션은 메소드의 반환 객체를 스프링 컨테이너에 빈등록해주는 어노테이션이다.
//    주로 @Configuration 어노테이션이 붙은 클래스 내부에서 사용한다.
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        /*
        JSP에서는 우리가 직접 SqlSessionFactory 객체를 설정하여 만들어 사용했었다.
        Spring에서는 SqlSessionFactory객체도 Bean으로 만들어 스프링 컨테이너가 관리하도록 한다.
        SqlSessionFactoryBean 객체에 설정을 해주면 팩토리를 지어주는 SqlSessionFactoryBuilder의 역할을 수행하며
        그렇게 만들어진 SqlSessionFactory를 스프링 컨테이너가 관리하게 된다.
         */
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/**/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));

        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}



























