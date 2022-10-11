package br.com.gerencianet.pix.cobv.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixUpdateDueCharge {
    public static void main(String[] args) throws IOException {

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("devedor",new JSONObject().put("logradouro", "Alameda Souza, Numero 80, Bairro Braz")
			.put("cidade", "Recife")
			.put("uf", "PE")
			.put("cep", "70011750")
			.put("cpf", "12345678909")
			.put("nome", "Francisco da Silva"));
		body.put("valor", new JSONObject().put("original", "123.45"));
		body.put("solicitacaoPagador", "Cobrança dos serviços prestados.");

			try {
				Gerencianet gn = new Gerencianet(options);
				Map<String, Object> response = gn.call("pixUpdateDueCharge", params, body);
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