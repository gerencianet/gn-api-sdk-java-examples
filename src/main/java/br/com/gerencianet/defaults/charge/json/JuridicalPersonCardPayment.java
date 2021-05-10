package br.com.gerencianet.defaults.charge.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class JuridicalPersonCardPayment {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		String paymentToken = "payment_token";
		
		JSONObject juridicalData = new JSONObject();
		juridicalData.put("corporate_name", "Company Name");
		juridicalData.put("cnpj", "99794567000144");

		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");
		customer.put("email", "oldbuck@gerencianet.com.br");
		customer.put("birth", "1977-01-15");
		customer.put("juridical_person", juridicalData);

		JSONObject billingAddress = new JSONObject();
		billingAddress.put("street", "Av. JK");
		billingAddress.put("number", 909);
		billingAddress.put("neighborhood", "Bauxita");
		billingAddress.put("zipcode", "5400000");
		billingAddress.put("city", "Ouro Preto");
		billingAddress.put("state", "MG");

		JSONObject creditCard = new JSONObject();
		creditCard.put("installments", 1);
		creditCard.put("billing_address", billingAddress);
		creditCard.put("payment_token", paymentToken);
		creditCard.put("customer", customer);

		JSONObject payment = new JSONObject();
		payment.put("credit_card", creditCard);

		JSONObject body = new JSONObject();
		body.put("payment", payment);

		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("payCharge", params, body);
			System.out.println(response);
		}catch (GerencianetException e){
			System.out.println(e.getCode());
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
