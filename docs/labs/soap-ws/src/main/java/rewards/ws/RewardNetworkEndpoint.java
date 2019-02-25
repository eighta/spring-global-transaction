package rewards.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.ws.types.RewardAccountForDiningRequest;
import rewards.ws.types.RewardAccountForDiningResponse;

@Endpoint
public class RewardNetworkEndpoint {

	private static final String NAMESPACE_URI = "http://www.springsource.com/reward-network";

	private RewardNetwork rewardNetwork;

	@Autowired
	public RewardNetworkEndpoint(RewardNetwork rewardNetwork) {
		this.rewardNetwork = rewardNetwork;
	}
	
	// TODO 06: Create a new method to handle the SOAP request payload.
	//	Use an annotation to cause Spring WS to route dining requests to this method.
	//	The method should take a RewardAccountForDiningRequest parameter.
	//	Use an annotation to instruct Spring WS to unmarshalled the request payload.
	//	Processes the request:
	//		Create a Dining object (hint: see Dining.createDining())
	//		Call rewardNetwork.rewardAccountFor() with this dining object.
	//		Assign the return value to local variable.  
	//		Create a new RewardAccountForDiningResponse with this variable
	//		Set the account number and confirmation number properties on this response object.
	//		Return the response object.  Use an annotation to instruct Spring WS to marshall the response payload.
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="rewardAccountForDiningRequest")
	public @ResponsePayload RewardAccountForDiningResponse elNombreQueYoQuiera
		(@RequestPayload RewardAccountForDiningRequest  request){
		
		Dining dining = Dining.createDining(request.getAmount().toString(), request.getCreditCardNumber().toString(), request.getMerchantNumber().toString());
		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
	
		RewardAccountForDiningResponse response = new RewardAccountForDiningResponse();
		response.setAccountNumber(confirmation.getAccountContribution().getAccountNumber());
		response.setAmount(confirmation.getAccountContribution().getAmount().asBigDecimal());
		response.setConfirmationNumber(confirmation.getConfirmationNumber());
		return response;
	
	}

}