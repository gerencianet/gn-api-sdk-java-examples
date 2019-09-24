package br.com.gerencianet.charge.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepCardMarketPlace {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();
		
		// repasses
        
		HashMap<String, Object> repass_1 = new HashMap<String, Object>();
        repass_1.put("payee_code", "Insira_aqui_o_indentificador_da_conta_destino");
        repass_1.put("percentage", 1500);
        
        HashMap<String, Object> repass_2 = new HashMap<String, Object>();
        repass_2.put("payee_code", "Insira_aqui_o_indentificador_da_conta_destino");
        repass_2.put("percentage", 2500);

        List<Object> repass =  new ArrayList<Object>();
        repass.add(repass_1);
        repass.add(repass_1);
        
        HashMap<String, Object> repasses = new HashMap<String, Object>();
        repasses.put("repasses", repass);

		HashMap<String, Object> options = new HashMap<String, Object>();
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
		
		String paymentToken = "InsiraAquiUmPayeementeCode";
		
		Map<String, Object> customer = new HashMap<String, Object>();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");
		customer.put("email", "oldbuck@gerencianet.com.br");
		customer.put("birth", "1977-01-15");
		
		Map<String, Object> billingAddress = new HashMap<String, Object>();
		billingAddress.put("street", "Av. JK");
		billingAddress.put("number", 909);
		billingAddress.put("neighborhood", "Bauxita");
		billingAddress.put("zipcode", "35400000");
		billingAddress.put("city", "Ouro Preto");
		billingAddress.put("state", "MG");

		Map<String, Object> creditCard = new HashMap<String, Object>();
		creditCard.put("installments", 1);
		creditCard.put("billing_address", billingAddress);
		creditCard.put("payment_token", paymentToken);
		creditCard.put("customer", customer);

		Map<String, Object> payment = new HashMap<String, Object>();
		payment.put("credit_card", creditCard);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("payment", payment);
		body.put("items", items);
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    Map<String, Object> response = gn.call("oneStep", new HashMap<String,String>(), body);
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
