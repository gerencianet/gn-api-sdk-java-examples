package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class LinkCharge {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");
		
		JSONObject body = new JSONObject();
		body.put("billet_discount", 10);
		body.put("card_discount", 10);
		body.put("message", "link test");
		body.put("expire_at", "2018-12-12");
		body.put("request_delivery_address", false);
		body.put("payment_method", "all");
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject response = gn.call("linkCharge", params, body);
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
