package br.com.gerencianet.pix.location.map;

import java.util.HashMap;

import java.awt.Desktop;
import javax.imageio.ImageIO;

import org.json.JSONObject;

import br.com.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

import java.io.ByteArrayInputStream;
import java.io.File;

public class PixGenerateQRCode {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("id", " ");

        try {
          Gerencianet gn = new Gerencianet(options);
          JSONObject response = gn.call("pixGenerateQRCode", params, new JSONObject());
          
          File outputfile = new File("qrCodeImage.png");
          ImageIO.write(ImageIO.read(new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(((String) response.get("imagemQrcode")).split(",")[1]))), "png", outputfile);
          Desktop desktop = Desktop.getDesktop();
		      desktop.open(outputfile);
           
        }catch (GerencianetException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
        }
	  }
}
