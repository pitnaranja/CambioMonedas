import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.io.EOFException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    // Setting URL
    public static void main(String[] args) {

        System.out.println(" Bienvnidos");
        Scanner input = new Scanner(System.in);
        int seleccion=0;
        String target = null;

        do {
            System.out.println(seleccion);
            System.out.println("""
            Bienvenido a la aplicacion cambio de monedas:
            Elije una opción válida
            1-Euro a Dolar
            2-Euro a Pesos Argentinos
            3-Euro a Quetzales
            4-Salir
            """);
            seleccion = input.nextInt();

            switch (seleccion) {
                case 1:
                    target = "USD";
                    break;
                case 2:
                    target = "ARS";
                    break;
                case 3:
                    target = "GTQ";
                    break;
                case 4:
                    break;
            }
            if (seleccion !=4){
                System.out.println("Que cantidad es la quieres cambiar");
                double cantidad = input.nextDouble();


                try {
                    String url_str = "https://v6.exchangerate-api.com/v6/8a6d4d4bec7905bdca8410f0/pair/EUR/" + target ;
                    URL url;
                    url = new URL(url_str);
                    HttpURLConnection request;
                    request = (HttpURLConnection) url.openConnection();
                    request.connect();
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    JsonObject jsonobj = root.getAsJsonObject();

                    String req_result = jsonobj.get("result").getAsString();
                    String req_result1 = jsonobj.get("conversion_rate").getAsString();
                    double rate = Double.parseDouble(req_result1);
                    double cambio =rate * cantidad;
                    String cambioString = String.format("%.2f",cambio);
                    System.out.println(" el valor de " + cantidad + "  EUR son " +cambioString + " en " + target);


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }}

        } while (seleccion!=4);


    }
}
