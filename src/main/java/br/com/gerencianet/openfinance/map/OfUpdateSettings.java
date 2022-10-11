package br.com.gerencianet.openfinance.map;

import java.util.HashMap;
import java.util.Map;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class OfUpdateSettings {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        Map<String, Object> body = new HashMap<String, Object>();
		    body.put("redirectURL", "https://client.com/redirect/here");
        body.put("webhookURL", "https://client.com/send/callback/here");

        try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, Object> response = gn.call("ofUpdateSettings", new HashMap<String,String>(), body);
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
