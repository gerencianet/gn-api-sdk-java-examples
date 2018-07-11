package br.com.gerencianet.charge.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class CreateChargeBalanceSheet {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		
		JSONObject defOne = new JSONObject();
		defOne.put("align", "left");
		defOne.put("color", "#000000");
		defOne.put("style", "bold");
		defOne.put("text", "Exemplo de despesa");
		defOne.put("colspan", 2);
		
		JSONObject defTwo = new JSONObject();
		defTwo.put("align", "left");
		defTwo.put("color", "#000000");
		defTwo.put("style", "bold");
		defTwo.put("text", "Total lançado");
		defTwo.put("colspan", 2);
		
		JSONArray rowOne = new JSONArray();
		rowOne.put(defOne);
		rowOne.put(defTwo);
		
		JSONObject defThree = new JSONObject();
		defThree.put("align", "left");
		defThree.put("color", "#000000");
		defThree.put("style", "normal");
		defThree.put("text", "Instalação");
		defThree.put("colspan", 2);
		
		JSONObject defFour = new JSONObject();
		defFour.put("align", "left");
		defFour.put("color", "#000000");
		defFour.put("style", "normal");
		defFour.put("text", "R$ 100,00");
		defFour.put("colspan", 2);
		
		JSONArray rowTwo = new JSONArray();
		rowTwo.put(defThree);
		rowTwo.put(defFour);
		
		JSONArray rowsSideOne = new JSONArray();
		rowsSideOne.put(rowOne);
		rowsSideOne.put(rowTwo);
		
		JSONObject rowObjectSideOne = new JSONObject();
		rowObjectSideOne.put("rows", rowsSideOne);
		
		JSONArray tablesSideOne = new JSONArray();
		tablesSideOne.put(rowObjectSideOne);
		
		JSONObject sideOne = new JSONObject();
		sideOne.put("header", "Demonstrativo de Consumo");
		sideOne.put("tables", tablesSideOne);
		
		JSONObject defFive = new JSONObject();
		defFive.put("align", "left");
		defFive.put("color", "#000000");
		defFive.put("style", "normal");
		defFive.put("text", "Confira na documentação da Gerencianet todas as configurações possíveis de um boleto balancete.");
		defFive.put("colspan", 4);
		
		JSONArray rowThree = new JSONArray();
		rowThree.put(defFive);
		
		JSONArray rowsSideTwo = new JSONArray();
		rowsSideTwo.put(rowThree);
		
		JSONObject rowObjectSideTwo = new JSONObject();
		rowObjectSideTwo.put("rows", rowsSideTwo);
		
		JSONArray tablesSideTwo = new JSONArray();
		tablesSideTwo.put(rowObjectSideTwo);
		
		JSONObject sideTwo = new JSONObject();
		sideTwo.put("header", "Balancete Geral");
		sideTwo.put("tables", tablesSideTwo);
		
		JSONArray balanceSheet = new JSONArray();
		balanceSheet.put(sideOne);
		balanceSheet.put(sideTwo);
		
		JSONObject body = new JSONObject();
		body.put("title", "Balancete Demonstrativo");
		body.put("body", balanceSheet);
		

		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("createChargeBalanceSheet", params, body);
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
