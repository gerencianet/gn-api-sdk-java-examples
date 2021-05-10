package br.com.gerencianet.gn.key.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixCreateEvp {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("pix_cert", credentials.getCertificadoPix());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			
			Map<String, Object> response = gn.call("pixCreateEvp", new HashMap<String,String>(), new HashMap<String, Object>());
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