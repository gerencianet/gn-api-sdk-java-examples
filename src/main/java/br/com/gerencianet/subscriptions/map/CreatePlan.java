package br.com.gerencianet.subscriptions.map;

import java.util.HashMap;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class CreatePlan {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("name", "My plan");
		body.put("interval", 2);
		body.put("repeats", 2);

		try {
		    Gerencianet gn = new Gerencianet(options);
		    Map<String, Object> plan = gn.call("createPlan", new HashMap<String,String>(), body);
		    System.out.println(plan);
		}catch (GerencianetException e){
		    System.out.println(e.getCode());
		    System.out.println(e.getError());
		    System.out.println(e.getErrorDescription());
		}catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}
}
