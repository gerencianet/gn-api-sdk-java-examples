package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepCardMarketPlace {
	public static void main(String[] args) {
        /* *********  Set credentials parameters ******** */
        Credentials credentials = new Credentials();
        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());
        /* ************************************************* */         
        
        // repasses
        
        JSONObject repass_1 = new JSONObject();
        repass_1.put("payee_code", "Insira_aqui_o_indentificador_da_conta_destino");
        repass_1.put("percentage", 1500);
        
        JSONObject repass_2 = new JSONObject();
        repass_2.put("payee_code", "Insira_aqui_o_indentificador_da_conta_destino");
        repass_2.put("percentage", 2500);

        JSONArray repass = new JSONArray();
        repass.put(repass_1);
        repass.put(repass_2);
        
        JSONObject repasses = new JSONObject();
        repasses.put("repasses", repass);

        
        // items
        JSONArray items = new JSONArray();
        JSONObject item1 = new JSONObject();
        item1.put("name", "Item 1");
        item1.put("amount", 1);
        item1.put("value", 1000);
        item1.put("marketplace",repasses);
        items.put(item1);
        
        //JSONObject body = new JSONObject();
        //body.put("items", items);
        
        //customer
        String paymentToken = "InsiraAquiUmPayeementeCode";
		
		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");
		customer.put("email", "oldbuck@gerencianet.com.br");
		customer.put("birth", "1977-01-15");
		
		JSONObject billingAddress = new JSONObject();
		billingAddress.put("street", "Av. JK");
		billingAddress.put("number", 909);
		billingAddress.put("neighborhood", "Bauxita");
		billingAddress.put("zipcode", "35400000");
		billingAddress.put("city", "Ouro Preto");
		billingAddress.put("state", "MG");
        
		//URL de notificações
        JSONObject metadata = new JSONObject();
        metadata.put("notification_url", "https://seu.dominio/retorno");
        metadata.put("custom_id", "id_0007");
        
        //desconto        
        JSONObject discount = new JSONObject();
        discount.put("type","currency");
        discount.put("value",500);
        
        
        JSONObject creditCard = new JSONObject();
		creditCard.put("installments", 1);
		creditCard.put("billing_address", billingAddress);
		creditCard.put("payment_token", paymentToken);
		creditCard.put("customer", customer);
		creditCard.put("discount", discount);

		JSONObject payment = new JSONObject();
		payment.put("credit_card", creditCard);

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
