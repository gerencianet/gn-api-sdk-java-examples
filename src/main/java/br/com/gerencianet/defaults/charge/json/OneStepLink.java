package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepLink {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 		
		
		// items
		JSONArray items = new JSONArray();

		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 4");
		item1.put("amount", 1);
		item1.put("value", 500);

		JSONObject item2 = new JSONObject("{\"name\":\"Item 2\", \"amount\":1, \"value\":1000}");

		items.put(item1);
		items.put(item2);

		JSONObject settings = new JSONObject();		
		settings.put("payment_method", "all");
		settings.put("billet_discount", 10);
		settings.put("card_discount", 10);
		settings.put("expire_at", "2022-12-12");
		settings.put("request_delivery_address", false);
		
		//notification url
		JSONObject metadata = new JSONObject();
		metadata.put("notification_url", "https://seu.dominio/retorno");
		
		JSONObject body = new JSONObject();
		body.put("items", items);
		body.put("settings", settings);
		body.put("metadata", metadata);
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject response = gn.call("oneStepLink", new HashMap<String,String>(), body);
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
