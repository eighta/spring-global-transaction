package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GlobalTxSvc {
	
	@Autowired
	MySqlSvc mySqlSvc;
	
	@Autowired
	PostgreSQLSvc postgreSQLSvc;

	@Transactional(transactionManager="betaTransactionManager")
	public void create(String run) {
		postgreSQLSvc.create(run,run);		
		mySqlSvc.create(run);
	}

}
