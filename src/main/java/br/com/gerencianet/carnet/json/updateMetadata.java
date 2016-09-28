package br.com.gerencianet.carnet.json;

import java.util.HashMap;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class updateMetadata {

	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */
		
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());
		
		/* ************************************************* */ 
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");
		
		JSONObject body = new JSONObject();
		body.put("custom_id", "Carnet 0001");
		body.put("notification_url", "http://domain.com/notification");
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("updateCarnetMetadata", params, body);
			System.out.println(response);
		}catch (GerencianetException e){
			System.out.println(e.getCode());
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
