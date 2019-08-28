package br.com.gerencianet.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepBillet {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("partner_token", credentials.getPartnerToken());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 		
		
		// items
		JSONArray items = new JSONArray();

		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 1");
		item1.put("amount", 1);
		item1.put("value", 600);

		JSONObject item2 = new JSONObject("{\"name\":\"Item 2\", \"amount\":1, \"value\":1000}");

		items.put(item1);
		items.put(item2);
		
		//customer
		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "94271564656");
		customer.put("phone_number", "5144916523");
		
		//notification url
		JSONObject metadata = new JSONObject();
		metadata.put("notification_url", "https://requestb.in/16rpx6y1");
		metadata.put("custom_id", "sou_fera_0007");
		
		//discount		
		JSONObject discount = new JSONObject();
		discount.put("type","currency");
		discount.put("value",599);
		
		//configurations
		JSONObject configurations = new JSONObject();
		configurations.put("fine", 200);
		configurations.put("interest", 33);
		
		//conditional discount
		JSONObject conditional_discount = new JSONObject();
		conditional_discount.put("type","percentage");
		conditional_discount.put("value", 500);
		conditional_discount.put("until_date", "2019-08-30");
		
		
		JSONObject bankingBillet = new JSONObject();
		bankingBillet.put("expire_at", "2019-09-15");
		bankingBillet.put("customer", customer);
		bankingBillet.put("discount", discount);
		bankingBillet.put("configurations", configurations);
		bankingBillet.put("conditional_discount", conditional_discount);
		

		JSONObject payment = new JSONObject();
		payment.put("banking_billet", bankingBillet);

		JSONObject body = new JSONObject();
		body.put("payment", payment);
		body.put("items", items);
		body.put("metadata", metadata);
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject response = gn.call("oneStep", new HashMap<String,String>(), body);
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
