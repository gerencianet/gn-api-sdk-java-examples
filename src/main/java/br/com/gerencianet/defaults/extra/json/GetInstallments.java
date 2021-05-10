package br.com.gerencianet.defaults.extra.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class GetInstallments {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("total", "20000");
		params.put("brand", "visa");
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject installments = gn.call("getInstallments", params, new JSONObject());
		    System.out.println(installments);
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
