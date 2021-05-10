package br.com.gerencianet.defaults.subscriptions.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PaySubscription {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");

		JSONObject bankingBillet = new JSONObject();
		bankingBillet.put("expire_at", "2018-12-12");
		bankingBillet.put("customer", customer);

		JSONObject payment = new JSONObject();
		payment.put("banking_billet", bankingBillet);

		JSONObject body = new JSONObject();
		body.put("payment", payment);

		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject response = gn.call("paySubscription", params, body);
		    System.out.println(response);
		}catch (GerencianetException e){
		    System.out.println(e.getCode());
		    System.out.println(e.getError());
		    System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}
}
