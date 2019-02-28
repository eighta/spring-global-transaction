package conf;

import java.util.List;

import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

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
	
//	//use an XA-aware DataSource wrapper for Atomikos
//	@Bean
//	public AtomikosDataSourceBean atomikosDataSource(
//			@Autowired
//			DataSource alphaDataSource
//			) throws SQLException {
//		
//		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//		atomikosDataSourceBean.setUniqueResourceName("XADBMS");
//		atomikosDataSourceBean.setXaDataSource((XADataSource)alphaDataSource);
//		return atomikosDataSourceBean;
//	}
	
	//Chaining transaction managers
	@Bean
	public ChainedTransactionManager chainedTransactionManager(
			@Autowired
			List<PlatformTransactionManager> platformTransactionManagerList) {
		
		return new ChainedTransactionManager(
				platformTransactionManagerList.toArray(
						new PlatformTransactionManager[platformTransactionManagerList.size()]) );
	}
}
