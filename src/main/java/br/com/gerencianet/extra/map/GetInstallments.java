package br.com.gerencianet.extra.map;

import java.util.HashMap;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class GetInstallments {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("total", "20000");
		params.put("brand", "visa");
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    Map<String, Object> installments = gn.call("getInstallments", params, new HashMap<String, Object>());
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
