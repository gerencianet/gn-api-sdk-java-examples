package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OneStepBilletMarketPlace {
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
        JSONObject customer = new JSONObject();
        customer.put("name", "Gorbadoc Oldbuck");
        customer.put("cpf", "94271564656");
        customer.put("phone_number", "5144916523");
        
        //URL de notificações
        JSONObject metadata = new JSONObject();
        metadata.put("notification_url", "https://seu.dominio/retorno");
        metadata.put("custom_id", "id_0007");
        
        //desconto        
        JSONObject discount = new JSONObject();
        discount.put("type","currency");
        discount.put("value",400);
        
        //juros e mora
        JSONObject configurations = new JSONObject();
        configurations.put("fine", 200);
        configurations.put("interest", 33);
        
        //disconto condicional
        JSONObject conditional_discount = new JSONObject();
        conditional_discount.put("type","percentage");
        conditional_discount.put("value", 100);
        conditional_discount.put("until_date", "2019-09-13");
        
        
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
