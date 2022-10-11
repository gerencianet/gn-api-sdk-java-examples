package br.com.gerencianet.gn.account.json;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixUpdateSettings {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		JSONObject body = new JSONObject();
		body.put("pix", new JSONObject()
			.put("receberSemChave", true)
			.put("chaves", new JSONObject()
			.put("sua_chave", new JSONObject()
			.put("recebimento", new JSONObject()
			.put("txidObrigatorio", true)
			.put("qrCodeEstatico", new JSONObject()
			.put("recusarTodos", false))))));

			try {
				Gerencianet gn = new Gerencianet(options);
				JSONObject response = gn.call("pixUpdateSettings", new HashMap<String,String>(), body);
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