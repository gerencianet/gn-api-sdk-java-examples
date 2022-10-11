package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class Marketplace {
	public static void main(String[] args) 
	{
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */
        
        JSONObject marketplace = new JSONObject();
        JSONArray repasses = new JSONArray();

        JSONObject repasse1 = new JSONObject();
        repasse1.put("payee_code", "payee_code");
        repasse1.put("percentage", 2500);

        JSONObject repasse2 = new JSONObject();
        repasse2.put("payee_code", "another_payee_code");
        repasse2.put("percentage", 2500);

        repasses.put(repasse1);
        repasses.put(repasse2);

        marketplace.put("repasses", repasses);

        JSONArray items = new JSONArray();

        JSONObject item = new JSONObject();
        item.put("name", "Item 1");
        item.put("amount", 1);
        item.put("value", 2000);
        item.put("marketplace", marketplace);
        items.put(item);

        JSONObject body = new JSONObject();
        body.put("items", items);

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("createCharge", new HashMap<String,String>(), body);
            System.out.println(response);
        }catch (GerencianetException e){
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
