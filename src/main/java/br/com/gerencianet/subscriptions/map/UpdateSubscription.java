package br.com.gerencianet.subscriptions.map;

import java.util.HashMap;
import java.util.Map;
import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class UpdateSubscription {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("notification_url", "http://localhost");
		body.put("custom_id", "Custom Subscription 0001");
		
		try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, Object> subscription = gn.call("updateSubscriptionMetadata", params, body);
            System.out.println(subscription);
        }catch (GerencianetException e){
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

	}
}
