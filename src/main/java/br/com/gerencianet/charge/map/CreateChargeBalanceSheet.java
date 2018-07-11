package br.com.gerencianet.charge.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class CreateChargeBalanceSheet {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		
		Map<String, Object> defOne = new HashMap<String, Object>();
		defOne.put("align", "left");
		defOne.put("color", "#000000");
		defOne.put("style", "bold");
		defOne.put("text", "Exemplo de despesa");
		defOne.put("colspan", 2);
		
		Map<String, Object> defTwo = new HashMap<String, Object>();
		defTwo.put("align", "left");
		defTwo.put("color", "#000000");
		defTwo.put("style", "bold");
		defTwo.put("text", "Total lançado");
		defTwo.put("colspan", 2);
		
		List<Object> rowOne =  new ArrayList<Object>();
		rowOne.add(defOne);
		rowOne.add(defTwo);
		
		Map<String, Object> defThree = new HashMap<String, Object>();
		defThree.put("align", "left");
		defThree.put("color", "#000000");
		defThree.put("style", "normal");
		defThree.put("text", "Instalação");
		defThree.put("colspan", 2);
		
		Map<String, Object> defFour = new HashMap<String, Object>();
		defFour.put("align", "left");
		defFour.put("color", "#000000");
		defFour.put("style", "normal");
		defFour.put("text", "R$ 100,00");
		defFour.put("colspan", 2);
		
		List<Object> rowTwo = new ArrayList<Object>();
		rowTwo.add(defThree);
		rowTwo.add(defFour);
		
		List<Object> rowsSideOne = new ArrayList<Object>();
		rowsSideOne.add(rowOne);
		rowsSideOne.add(rowTwo);
		
		Map<String, Object> rowObjectSideOne = new HashMap<String, Object>();
		rowObjectSideOne.put("rows", rowsSideOne);
		
		List<Object> tablesSideOne = new ArrayList<Object>();
		tablesSideOne.add(rowObjectSideOne);
		
		Map<String, Object> sideOne = new HashMap<String, Object>();
		sideOne.put("header", "Demonstrativo de Consumo");
		sideOne.put("tables", tablesSideOne);
		
		Map<String, Object> defFive = new HashMap<String, Object>();
		defFive.put("align", "left");
		defFive.put("color", "#000000");
		defFive.put("style", "normal");
		defFive.put("text", "Confira na documentação da Gerencianet todas as configurações possíveis de um boleto balancete.");
		defFive.put("colspan", 4);
		
		List<Object> rowThree = new ArrayList<Object>();
		rowThree.add(defFive);
		
		List<Object> rowsSideTwo = new ArrayList<Object>();
		rowsSideTwo.add(rowThree);
		
		Map<String, Object> rowObjectSideTwo = new HashMap<String, Object>();
		rowObjectSideTwo.put("rows", rowsSideTwo);
		
		List<Object> tablesSideTwo = new ArrayList<Object>();
		tablesSideTwo.add(rowObjectSideTwo);
		
		Map<String, Object> sideTwo = new HashMap<String, Object>();
		sideTwo.put("header", "Balancete Geral");
		sideTwo.put("tables", tablesSideTwo);
		
		List<Object> balanceSheet = new ArrayList<Object>();
		balanceSheet.add(sideOne);
		balanceSheet.add(sideTwo);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("title", "Balancete Demonstrativo");
		body.put("body", balanceSheet);
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    Map<String, Object> response = gn.call("createChargeBalanceSheet", params, body);
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
