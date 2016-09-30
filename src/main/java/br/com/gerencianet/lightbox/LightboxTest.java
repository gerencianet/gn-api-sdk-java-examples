package br.com.gerencianet.lightbox;

import java.util.Map;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class LightboxTest {

	public static void main(String[] args) {
		try {
			CheckoutLightbox clb = new CheckoutLightbox();
			clb.createCharge();
			Map<String, Object> response = clb.payCharge();
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
