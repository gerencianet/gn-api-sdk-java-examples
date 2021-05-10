package br.com.gerencianet.pix.pix.json;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixDevolution {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("pix_cert", credentials.getCertificadoPix());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("e2eId", " ");
        params.put("id", " ");

        JSONObject body = new JSONObject();
		    body.put("valor", "0.01");

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("pixDevolution", params, body);
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
