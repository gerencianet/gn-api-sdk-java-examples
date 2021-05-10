package br.com.gerencianet.pix.location.map;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixCreateLocation {
    public static void main(String[] args) throws IOException {

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("pix_cert", credentials.getCertificadoPix());
		options.put("sandbox", credentials.isSandbox());

		JSONObject body = new JSONObject();
		body.put("tipoCob", "cob");

			try {
				Gerencianet gn = new Gerencianet(options);
				JSONObject response = gn.call("pixCreateLocation", new HashMap<String,String>(), body);
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