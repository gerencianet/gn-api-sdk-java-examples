package br.com.gerencianet.pix.charge.json;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixUpdateCharge {
    public static void main(String[] args) throws IOException {
      Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("pix_cert", credentials.getCertificadoPix());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", " ");

        JSONObject body = new JSONObject();
        body.put("valor", new JSONObject().put("original", "5.00"));

        try {
          Gerencianet gn = new Gerencianet(options);
          JSONObject response = gn.call("pixUpdateCharge", params, body);
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