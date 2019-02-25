package conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

@Configuration
@EnableJpaRepositories(
		basePackages="alpha.repositories", 
	entityManagerFactoryRef="alphaEntityManagerFactory", 
	transactionManagerRef="alphaTransactionManager")
@EnableTransactionManagement
public class AlphaDbConfig {

	@Bean
	public DataSource alphaDataSource() {
		
		AtomikosNonXADataSourceBean atomikosNonXADataSourceBean = new AtomikosNonXADataSourceBean();
		atomikosNonXADataSourceBean.setUniqueResourceName("postgres");
		atomikosNonXADataSourceBean.setDriverClassName("org.postgresql.Driver");
		atomikosNonXADataSourceBean.setUrl("jdbc:postgresql://localhost:5432/alpha");
		atomikosNonXADataSourceBean.setUser("postgres");
		atomikosNonXADataSourceBean.setPassword("sophie");
		atomikosNonXADataSourceBean.setPoolSize(4);
		
		
		return atomikosNonXADataSourceBean;
		
		/*
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName("org.postgresql.Driver");
		  dataSource.setUrl("jdbc:postgresql://localhost:5432/alpha");
		  dataSource.setUsername("postgres");
		  dataSource.setPassword("sophie");
		  */
		 
		//H2
		/*
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		
		*/
		//  return dataSource;
	}

	//ENTITY MANAGER
	@Bean
	public LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setPersistenceUnitName("ALPHA_PUN");
		em.setDataSource(alphaDataSource());
		em.setPackagesToScan(new String[] { "alpha.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		//properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		//properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.ProgressDialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}
	
	
	//TRANSACTION MANAGER
	@Bean
    public PlatformTransactionManager alphaTransactionManager(
    		//@Autowired EntityManagerFactory emf
    		) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(emf);
        transactionManager.setEntityManagerFactory(alphaEntityManagerFactory().getObject());
        return transactionManager;
    }

}
