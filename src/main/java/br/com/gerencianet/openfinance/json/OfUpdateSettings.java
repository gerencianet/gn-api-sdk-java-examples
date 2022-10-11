package br.com.gerencianet.openfinance.json;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OfUpdateSettings {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
		    body.put("redirectURL", "https://client.com/redirect/here");
        body.put("webhookURL", "https://client.com/send/callback/here");

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("ofUpdateSettings", new HashMap<String,String>(), body);
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
