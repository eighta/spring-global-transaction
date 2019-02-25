package conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
		basePackages="beta.repositories",
entityManagerFactoryRef="betaEntityManagerFactory", 
transactionManagerRef="betaTransactionManager")
@EnableTransactionManagement
public class BetaDbConfig {

	@Bean
	public DataSource betaDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/beta");
		dataSource.setUsername("root");
		dataSource.setPassword("sophie");
		return dataSource;
	}

	// ENTITY MANAGER
	@Bean
	public LocalContainerEntityManagerFactoryBean betaEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setPersistenceUnitName("BETA_PUN");
		em.setDataSource(betaDataSource());
		em.setPackagesToScan(new String[] { "beta.entities" });

		//HIBERNATE
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(additionalProperties());
		
		JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(eclipseLinkAdditionalProperties());

		return em;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		// properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
	}
	
	Properties eclipseLinkAdditionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("eclipselink.weaving", "false");
		return properties;
	}

	// TRANSACTION MANAGER
	@Bean
	public PlatformTransactionManager betaTransactionManager(
			//@Autowired EntityManagerFactory emf
			) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		//transactionManager.setEntityManagerFactory(emf);
		transactionManager.setEntityManagerFactory(betaEntityManagerFactory().getObject());
		return transactionManager;
	}
}
