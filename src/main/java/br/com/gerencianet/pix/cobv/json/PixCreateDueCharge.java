package br.com.gerencianet.pix.cobv.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixCreateDueCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

      HashMap<String, String> params = new HashMap<String, String>();
      params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

      JSONObject abatimento = new JSONObject().put("modalidade", 1).put("valorPerc", "5.00");
      JSONObject multa = new JSONObject().put("modalidade", 2).put("valorPerc", "15.00");      
      JSONObject juros = new JSONObject().put("modalidade", 2).put("valorPerc", "2.00");
      JSONObject desconto = new JSONObject().put("modalidade", 3).put("valorPerc", "1.00");

      JSONArray infoAdicionais = new JSONArray();
      infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));

      JSONObject body = new JSONObject();
      body.put("calendario", new JSONObject().put("dataDeVencimento", "2022-10-30").put("validadeAposVencimento", 30));
      body.put("devedor", new JSONObject().put("cpf", "12345678909")
          .put("nome", "Francisco da Silva")
          .put("logradouro", "Alameda Souza, Numero 80, Bairro Braz")
          .put("cidade", "Recife")
          .put("uf", "PE")
          .put("cep", "70011750"));
      body.put("valor", new JSONObject().put("original", "123.45")
          .put("abatimento", abatimento).put("desconto", desconto)
          .put("multa", multa)
          .put("juros", juros)
          .put("desconto", desconto));
      body.put("chave", "Insira_aqui_sua_chave");
      body.put("solicitacaoPagador", "Serviço realizado.");
      body.put("infoAdicionais", infoAdicionais);

        try {
          Gerencianet gn = new Gerencianet(options);
          JSONObject response = gn.call("pixCreateDueCharge", params, body);
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
