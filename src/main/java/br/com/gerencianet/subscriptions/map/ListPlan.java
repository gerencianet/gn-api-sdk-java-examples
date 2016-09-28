package br.com.gerencianet.subscriptions.map;

import java.util.HashMap;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class ListPlan {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "My plan");
		params.put("limit", "20");
		params.put("offset", "0");
		
		try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, Object> plans = gn.call("getPlans", params, new HashMap<String, Object>());
            System.out.println(plans);
        }catch (GerencianetException e){
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
