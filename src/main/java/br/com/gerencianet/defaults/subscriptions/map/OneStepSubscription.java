package br.com.gerencianet.defaults.subscriptions.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepSubscription {
    public static void main(String[] args) {
    	
        /* *********  Set credentials parameters ******** */
        Credentials credentials = new Credentials();
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());
        
        /* ************************************************* */
        HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

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
        customer.put("cpf", "94271564656");
        customer.put("phone_number", "5144916523");

        Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("notification_url", "http://domain.com/notification");

        Map<String, Object> discount = new HashMap<String, Object>();
        discount.put("type","currency");
		discount.put("value",599);
        
        Map<String, Object> configurations = new HashMap<String, Object>();
		configurations.put("fine", 200);
		configurations.put("interest", 33);
        
        Map<String, Object> bankingBillet = new HashMap<String, Object>();
		bankingBillet.put("expire_at", "2022-12-12");
		bankingBillet.put("customer", customer);
        bankingBillet.put("discount", discount);
		bankingBillet.put("configurations", configurations);

		Map<String, Object> payment = new HashMap<String, Object>();
		payment.put("banking_billet", bankingBillet);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("payment", payment);
        body.put("items", items);
		body.put("metadata", metadata);
        
        try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, Object> response = gn.call("oneStepSubscription", params, body);
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
