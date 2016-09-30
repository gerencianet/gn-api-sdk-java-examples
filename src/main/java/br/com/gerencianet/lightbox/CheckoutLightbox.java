package br.com.gerencianet.lightbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;

public class CheckoutLightbox {
	private String chargeId;
	private Gerencianet gn;
	
	public CheckoutLightbox() throws Exception
	{
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		
		this.gn = new Gerencianet(options);
	}
	
	public void createCharge() throws Exception{

		List<Object> items = new ArrayList<Object>();

		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("name", "Item 1");
		item1.put("amount", 1);
		item1.put("value", 1000);

		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("name", "Item 2");
		item2.put("amount", 1);
		item2.put("value", 2000);

		items.add(item1);
		items.add(item2);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("items", items);
		
		Map<String, Object> response = gn.call("createCharge", new HashMap<String,String>(), body);
		@SuppressWarnings("unchecked")
		HashMap<String, Object> data = (HashMap<String, Object>) response.get("data");
		this.chargeId = data.get("charge_id").toString();
	}
	
	public Map<String, Object> payCharge() throws Exception{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", this.chargeId);

		Map<String, Object> customer = new HashMap<String, Object>();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");

		Map<String, Object> bankingBillet = new HashMap<String, Object>();
		bankingBillet.put("expire_at", "2018-12-12");
		bankingBillet.put("customer", customer);

		Map<String, Object> payment = new HashMap<String, Object>();
		payment.put("banking_billet", bankingBillet);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("payment", payment);

		Map<String, Object> response = gn.call("payCharge", params, body);
		return response;
	}
}

