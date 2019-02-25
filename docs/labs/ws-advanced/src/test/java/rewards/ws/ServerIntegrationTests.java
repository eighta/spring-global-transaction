package rewards.ws;


import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/rewardNetwork-servlet-config.xml",
		              "file:src/main/webapp/WEB-INF/rewardNetwork-webapp-config.xml"})
public class ServerIntegrationTests {
    MockWebServiceClient mockClient;
    @Autowired ApplicationContext applicationContext;
    
    @Before
    public void createClient() {
       
    	/* TODO 03: Autowire the ApplicationContext into a field.  Use it
    	 * 			to create the MockWebServiceClient. */
    	 mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void validRequest() {
    
    	/* TODO 04: Create Source variables for a valid request and response pair
    	 * 			and verify that the request results in the given response.
    	 * 			Hint: the logging we added earlier provides good example 
    	 * 			XML for requests and responses.
    	 * 			Run the test and notice that it fails.  Can you tell
    	 * 			why the failure occurs?  */ 
    	
    	 Source requestPayload = new StringSource(
    	          "<ns2:rewardAccountForDiningRequest xmlns:ns2=\"http://www.springsource.com/reward-network\" amount=\"100.00\" creditCardNumber=\"1234123412341234\" merchantNumber=\"1234567890\"/>");
    	        Source responsePayload = new StringSource(
    	          "<ns2:rewardAccountForDiningResponse xmlns:ns2=\"http://www.springsource.com/reward-network\" accountNumber=\"123456789\" amount=\"8.00\" confirmationNumber=\"1\"/>");
    	        mockClient.sendRequest(withPayload(requestPayload))
    	          .andExpect(payload(responsePayload));
    }
    
    @Test
    public void invalidRequestWithoutCreditCardNumber() {
        
    	/* TODO 06: Using the previous test as a guide, create a request that is
    	 * 	MISSING the creditCardNumber attribute.  After calling the server,
    	 * 	assert that the returned fault is a 'Client' or 'Sender' fault.
    	 * 	Run this test, it should fail as we have not yet implemented
    	 * 	server-side schema validation. */
    	
    	Source requestPayload = new StringSource(
    	          "<ns2:rewardAccountForDiningRequest xmlns:ns2=\"http://www.springsource.com/reward-network\" amount=\"100.00\" merchantNumber=\"1234567890\"/>");
    	mockClient.sendRequest(withPayload(requestPayload))
        .andExpect(clientOrSenderFault());
        
    	
    }
    
    @Test
    public void invalidRequestWithUnknownCreditCardNumber() {
        
    	/* TODO 08: Using the original test as a guide, create a test with an
    	 * 	INVALID creditCardNumber attribute value.  After calling the server,
    	 * 	assert that the returned fault is a 'Client' or 'Sender' fault.
    	 * 	Run this test, it should fail as we have not yet implemented
    	 * 	server-side exception mapping.  Note the exception thrown. */
    	
    	Source requestPayload = new StringSource(
    	          "<ns2:rewardAccountForDiningRequest xmlns:ns2=\"http://www.springsource.com/reward-network\" creditCardNumber=\"1234123412341235\" amount=\"100.00\" merchantNumber=\"1234567890\"/>");
    	        mockClient.sendRequest(withPayload(requestPayload))
    	          .andExpect(clientOrSenderFault());
    }
    
}