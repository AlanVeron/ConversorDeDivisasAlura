import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class PrincipalConConsultasMonedas {
    public static void main(String[] args) {
        try {
            // URL de la API
            String url = "https://v6.exchangerate-api.com/v6/986757de83cfa130a47a73cd/latest/usd";
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            // Crear solicitud
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Deserializar el JSON con Gson
            Gson gson = new Gson();
            DatosDeJsonApiDivisas dataDelJsonApiMonedas = gson.fromJson(response.body(), DatosDeJsonApiDivisas.class);
            // Obtener las tasas de conversión
            Map<String, Double> monedaAconvertir = dataDelJsonApiMonedas.getConversionRates();
            Double valorDolar = (double) monedaAconvertir.get("USD");
            Double valorPesoArs = (double) monedaAconvertir.get("ARS");
            Double valorRealBrazil = (double) monedaAconvertir.get("BRL");
            Double valoPesoColombiano = (double) monedaAconvertir.get("COP");

            Scanner entradaUsuario = new Scanner(System.in);

            DatosDeReferenciaDivisasPaises muestraListaDePaises = new DatosDeReferenciaDivisasPaises();
            String muestraLista = muestraListaDePaises.muestraListaDeMonedas;

            int opcion = 0;
            String menu = """
                
                $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                --------------------------------------------------------
                    ---- Sea bienvenidx al Conversor de Divisas ---- 
                --------------------------------------------------------
                *** escriba el numero de la opcion deseada [ 1 a 10 ] ***
                       --(convertir de divisa A a divisa B ) --
                       --(modo: Automatico , opcion 1 a 6  ) --
                --------------------------------------------------------
                        1)       Dolar       =>>  Peso argentino
                        2)  Peso argentino   =>>  Dólar
                        3)       Dólar       =>>  Real brasileño
                        4)  Real brasileño   =>>  Dolar
                        5)       Dólar       =>>  Peso colombiano
                        6)  Peso colombiano  =>>  Dólar
                --------------------------------------------------------
                7) elegir entre dos divisas --(modo: Manual ) :
                       -- (ej: EUR,GBP,JPY,CAD,CNY,etc... )
                 ("listado de divisas admitidas en la opc 8")
                --------------------------------------------------------    
                8) ver listado de divisas admitidas
                --------------------------------------------------------
                9) tambien puede consultar el valor de  una moneda 
                especifica-( respecto a moneda base "USD" )
                --------------------------------------------------------
                10)                 - - - Salir  - - - 
                --------------------------------------------------------
                  
                """;

            while (opcion != 10 ){
                System.out.println(menu);
                opcion = entradaUsuario.nextInt();
                double montoAconvertirCase;
                double resultadoCase$;
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese la cantidad a convertir de 'usd' a 'ars' : ");
                            montoAconvertirCase = entradaUsuario.nextDouble();
                            resultadoCase$ = (montoAconvertirCase / valorDolar) * valorPesoArs;
                            System.out.println(" - el resultado de: "+montoAconvertirCase+" usd ,"+"es de :" +
                                    " "+ resultadoCase$ + " ars - ");
                            break;
                        case 2:
                            System.out.print("Ingrese la cantidad a convertir de 'ars' a 'usd' : ");
                            montoAconvertirCase = entradaUsuario.nextDouble();
                            resultadoCase$ = (montoAconvertirCase / valorPesoArs) * valorDolar;
                            System.out.println(" - el resultado de: "+montoAconvertirCase+" ars ,"+"es de :" +
                                    " "+ resultadoCase$ + " usd - ");
                            break;
                        case 3:
                            System.out.print("Ingrese la cantidad a convertir de 'usd ' a 'brz' : ");
                            montoAconvertirCase = entradaUsuario.nextDouble();
                            resultadoCase$ = (montoAconvertirCase / valorDolar) * valorRealBrazil;
                            System.out.println(" - el resultado de: "+montoAconvertirCase+" usd ,"+"es de :" +
                                    " "+ resultadoCase$ + " brl - ");
                            break;
                        case 4:
                            System.out.print("Ingrese la cantidad a convertir de 'brl ' a 'usd' : ");
                            montoAconvertirCase = entradaUsuario.nextDouble();
                            resultadoCase$ = (montoAconvertirCase / valorRealBrazil) * valorDolar;
                            System.out.println(" - el resultado de: "+montoAconvertirCase+" brl ,"+"es de :" +
                                    " "+ resultadoCase$ + " usd - ");
                            break;
                        case 5:
                            System.out.print("Ingrese la cantidad a convertir de 'usd ' a 'cop' : ");
                            montoAconvertirCase = entradaUsuario.nextDouble();
                            resultadoCase$ = (montoAconvertirCase / valorDolar) * valoPesoColombiano;
                            System.out.println(" - el resultado de: "+montoAconvertirCase+" usd ,"+"es de :" +
                                    " "+ resultadoCase$ + " cop - ");
                            break;
                        case 6:
                            System.out.print("Ingrese la cantidad a convertir de 'cop ' a 'usd' : ");
                            montoAconvertirCase = entradaUsuario.nextDouble();
                            resultadoCase$ = (montoAconvertirCase / valoPesoColombiano) * valorDolar;
                            System.out.println(" - el resultado de: "+montoAconvertirCase+" cop ,"+"es de :" +
                                    " "+ resultadoCase$ + " usd - ");
                            break;
                        case 7:
                            System.out.print("Ingrese el código de la moneda de origen (ejemplo: USD): ");
                            String monedaOrigen = entradaUsuario.next().toUpperCase();

                            System.out.print("Ingrese el código de la moneda de destino (ejemplo: ARS): ");
                            String monedaDestino = entradaUsuario.next().toUpperCase();

                            System.out.print("Ingrese la cantidad a convertir: ");
                            double montoAconvertir = entradaUsuario.nextDouble();

                            if (monedaAconvertir.containsKey(monedaOrigen) && monedaAconvertir.containsKey(monedaDestino)) {
                                double monedaOrigenFinal = monedaAconvertir.get(monedaOrigen);
                                double monedaDestinoFinal = monedaAconvertir.get(monedaDestino);
                                double result = (montoAconvertir / monedaOrigenFinal) * monedaDestinoFinal;

                                System.out.println("La conversión de " + montoAconvertir + " " + monedaOrigen + " a " + monedaDestino + " es: " + result);
                            } else {
                                System.out.println("Una o ambas monedas no se encuentran en las tasas de conversión.");
                            }
                            break;
                        case 8:
                            System.out.println(muestraLista);
                            break;
                        case 9:
                            System.out.print("Ingrese el código de la moneda (ejemplo: ARS): ");
                            String monedaPedidoDelUsuario = entradaUsuario.next().toUpperCase();

                            if (monedaAconvertir.containsKey(monedaPedidoDelUsuario)) {
                                System.out.println("El valor de " + monedaPedidoDelUsuario + " es: " + monedaAconvertir.get(monedaPedidoDelUsuario));
                            } else {
                                System.out.println("La moneda " + monedaPedidoDelUsuario + " no se encuentra en las tasas de conversión.");
                            }
                            break;
                        default:
                            System.out.println("$$  $$ -ERROR-  Elija una opción válida:  $$  $$\n");
                            break;
                    }
                }
//                System.out.println("Opción no válida.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Gracias por usar el convertidor de divisas - fin de app");
    }
}

