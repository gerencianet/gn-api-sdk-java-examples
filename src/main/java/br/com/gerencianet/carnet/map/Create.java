package br.com.gerencianet.carnet.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class 
Create {

	public static void main(String[] args) {		
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

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

		Map<String, Object> customer = new HashMap<String, Object>();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("items", items);
		body.put("customer", customer);
		body.put("expire_at", "2020-12-02");
		body.put("repeats", 5);
		body.put("split_items", false);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("createCarnet", new HashMap<String,String>(), body);
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
