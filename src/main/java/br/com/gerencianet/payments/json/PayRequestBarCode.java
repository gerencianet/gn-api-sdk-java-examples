package br.com.gerencianet.payments.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PayRequestBarCode {
    public static void main(String[] args) {

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("codBarras", "Insira_aqui_o_codBarras");

		JSONObject body = new JSONObject();
		body.put("dataPagamento", "2022-06-14");
		body.put("valor", 1000);
		body.put("descricao", "Pagamento de boleto");

			try {
				Gerencianet gn = new Gerencianet(options);
				JSONObject response = gn.call("payRequestBarCode", params, body);
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
