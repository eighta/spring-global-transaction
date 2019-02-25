package conf;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
public class XaConfig {

	@Bean
	public PlatformTransactionManager xaTransactionManager() throws SystemException {
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		
		jtaTransactionManager.setTransactionManager(new UserTransactionManager());
		
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(300);
		jtaTransactionManager.setUserTransaction(userTransactionImp);
		
		return jtaTransactionManager;
	}
	
	//use an XA-aware DataSource wrapper for Atomikos
	@Bean
	public AtomikosDataSourceBean atomikosDataSource(
			@Autowired
			DataSource alphaDataSource
			) throws SQLException {
		
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName("XADBMS");
		atomikosDataSourceBean.setXaDataSource((XADataSource)alphaDataSource);
		return atomikosDataSourceBean;
	}
}
