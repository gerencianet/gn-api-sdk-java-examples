package br.com.gerencianet.carnet.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class Create {

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
		
		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");
		
		JSONObject body = new JSONObject();
		body.put("items", items);
		body.put("customer", customer);
		body.put("expire_at", "2020-12-02");
		body.put("repeats", 5);
		body.put("split_items", false);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("createCarnet", new HashMap<String,String>(), body);
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
