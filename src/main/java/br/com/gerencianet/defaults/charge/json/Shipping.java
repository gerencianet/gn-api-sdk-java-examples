package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class Shipping {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		JSONArray items = new JSONArray();

		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 1");
		item1.put("amount", 1);
		item1.put("value", 2000);

		JSONObject item2 = new JSONObject("{\"name\":\"Item 1\", \"amount\":1, \"value\":1000}");

		items.put(item1);
		items.put(item2);
		
		JSONArray shippings = new JSONArray();

		JSONObject shipping1 = new JSONObject();
		shipping1.put("name", "My Shipping");
		shipping1.put("value", 2000);
		shippings.put(shipping1);
		
		JSONObject body = new JSONObject();
		body.put("items", items);
		body.put("shippings", shippings);
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject response = gn.call("createCharge", new HashMap<String,String>(), body);
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
