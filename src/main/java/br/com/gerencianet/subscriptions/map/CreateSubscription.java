package br.com.gerencianet.subscriptions.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class CreateSubscription {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "2262");

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

		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> subscription = gn.call("createSubscription", params, body);
			System.out.println(subscription);
		}catch (GerencianetException e){
			System.out.println(e.getCode());
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
