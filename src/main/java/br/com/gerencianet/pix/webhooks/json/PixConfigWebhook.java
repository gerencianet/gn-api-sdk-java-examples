package br.com.gerencianet.pix.webhooks.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixConfigWebhook {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

      options.put("x-skip-mtls-checking", "true");

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("chave", "sua_chave");

        JSONObject body = new JSONObject();
        body.put("webhookUrl", "https://exemplo-pix/webhook");

        try {
          Gerencianet gn = new Gerencianet(options);
          JSONObject response = gn.call("pixConfigWebhook", params, body);
          System.out.println(response);

        }catch (GerencianetException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
        }
	  }
}
