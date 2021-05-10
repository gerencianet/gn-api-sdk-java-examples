package br.com.gerencianet.defaults.marketplace.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
		Map<String, Object> marketplace = new HashMap<String, Object>();
		List<Object> repasses = new ArrayList<Object>();

		Map<String, Object> repasse1 = new HashMap<String, Object>();
        repasse1.put("payee_code", "payee_code");
        repasse1.put("percentage", 2500);

        Map<String, Object> repasse2 = new HashMap<String, Object>();
        repasse2.put("payee_code", "another_payee_code");
        repasse2.put("percentage", 2500);

        repasses.add(repasse1);
        repasses.add(repasse2);

        marketplace.put("repasses", repasses);

        List<Object> items = new ArrayList<Object>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("name", "Item 1");
        item.put("amount", 1);
        item.put("value", 2000);
        item.put("marketplace", marketplace);
        items.add(item);

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("items", items);

        try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, Object> response = gn.call("createCharge", new HashMap<String,String>(), body);
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
