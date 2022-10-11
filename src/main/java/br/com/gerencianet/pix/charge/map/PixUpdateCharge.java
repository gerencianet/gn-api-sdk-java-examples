package br.com.gerencianet.pix.charge.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;


import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixUpdateCharge {
    public static void main(String[] args) throws IOException {
      Credentials credentials = new Credentials();

      HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", " ");

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("valor", new HashMap<String, Object>().put("original", "5.00"));

        try {
          Gerencianet gn = new Gerencianet(options);
          Map<String, Object> response = gn.call("pixUpdateCharge", params, body);
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