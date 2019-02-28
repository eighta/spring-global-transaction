package a8;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import conf.AlphaDbConfig;
import conf.BetaDbConfig;
import conf.MainConfig;
import conf.XaConfig;
import services.GlobalTxSvc;

public class MainClass {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(
        		MainConfig.class
        		,AlphaDbConfig.class
        		,BetaDbConfig.class
        		,XaConfig.class
        		);
        ctx.refresh();
        
        String run = "28";
//        ctx.getBean(PostgreSQLSvc.class).create(run, run);
//        ctx.getBean(MySqlSvc.class).create(run);
        ctx.getBean(GlobalTxSvc.class).chainedTransactionManagerService(run);

                
        ctx.close();
	}
	
}
