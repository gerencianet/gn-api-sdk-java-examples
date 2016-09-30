package br.com.gerencianet.charge.map;

import java.util.HashMap;
import java.util.Map;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class JuridicalPersonCardPayment {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		String paymentToken = "payment_token";
		
		Map<String, Object> juridicalData = new HashMap<String, Object>();
		juridicalData.put("corporate_name", "Company Name");
		juridicalData.put("cnpj", "99794567000144");
		
		Map<String, Object> customer = new HashMap<String, Object>();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "04267484171");
		customer.put("phone_number", "5144916523");
		customer.put("email", "oldbuck@gerencianet.com.br");
		customer.put("birth", "1977-01-15");
		customer.put("juridical_person", juridicalData);

		Map<String, Object> billingAddress = new HashMap<String, Object>();
		billingAddress.put("street", "Av. JK");
		billingAddress.put("number", 909);
		billingAddress.put("neighborhood", "Bauxita");
		billingAddress.put("zipcode", "5400000");
		billingAddress.put("city", "Ouro Preto");
		billingAddress.put("state", "MG");

		Map<String, Object> creditCard = new HashMap<String, Object>();
		creditCard.put("installments", 1);
		creditCard.put("billing_address", billingAddress);
		creditCard.put("payment_token", paymentToken);
		creditCard.put("customer", customer);

		Map<String, Object> payment = new HashMap<String, Object>();
		payment.put("credit_card", creditCard);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("payment", payment);

		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("payCharge", params, body);
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
