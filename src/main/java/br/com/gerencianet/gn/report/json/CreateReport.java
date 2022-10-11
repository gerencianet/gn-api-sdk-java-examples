package br.com.gerencianet.gn.report.json;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class CreateReport {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		JSONObject body = new JSONObject();
		body.put("dataMovimento", "2022-04-24");
		body.put("tipoRegistros", new JSONObject().put("pixRecebido", true)
			.put("pixDevolucaoEnviada", false)
			.put("tarifaPixRecebido", true)
			.put("pixEnviadoChave", true)
			.put("pixEnviadoDadosBancarios", false)
			.put("pixDevolucaoRecebida", true));
 
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("createReport", new HashMap<String,String>(), body);
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