package br.com.gerencianet.pix.cobv.map;

import java.util.HashMap;
import java.util.Map;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixDetailDueCharge {
    public static void main(String[] args) {

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

    	HashMap<String, String> params = new HashMap<String, String>();
		params.put("txid", "7978c0c97ea847e78e8849634473c1f1");
		params.put("revisao", "0");

		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("pixDetailDueCharge", params, new HashMap<String, Object>());
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