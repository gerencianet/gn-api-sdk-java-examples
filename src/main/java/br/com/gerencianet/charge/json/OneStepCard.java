package br.com.gerencianet.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepCard {
    public static void main(String[] args) {
      
    	/* *********  Set credentials parameters ******** */
       Credentials credentials = new Credentials();
       JSONObject options = new JSONObject();
       options.put("client_id", credentials.getClientId());
       options.put("client_secret", credentials.getClientSecret());
       //options.put("partner_token", credentials.getPartnerToken());
       
       options.put("sandbox", credentials.isSandbox());
       /* ************************************************* */
       String paymentToken = "75a5955cc1fdb4185f2b89aa16fd96c6740a1b04";
       
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
       customer.put("email", "gorbadoc.oldbuck@gerencianet.com.br");
       customer.put("birth", "1990-05-04");
       
       //address
       JSONObject billingAddress = new JSONObject();
        billingAddress.put("street", "Av. JK");
        billingAddress.put("number", 909);
        billingAddress.put("neighborhood", "Bauxita");
        billingAddress.put("zipcode", "35400000");
        billingAddress.put("city", "Ouro Preto");
        billingAddress.put("state", "MG");
        
       //notification URL
       JSONObject metadata = new JSONObject();
       metadata.put("notification_url", "https://requestb.in/16rpx6y1");
       metadata.put("custom_id", "id_0007");
       
       //discount
       JSONObject discount = new JSONObject();
       discount.put("type","currency");
       discount.put("value",599);
       
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