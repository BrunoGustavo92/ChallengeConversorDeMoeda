import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultaMoedas {

    public ExchangeRate consultaValor(String moedas) {

        String chave = "abab99c02f4337b25fe4a932";
        String url = "https://v6.exchangerate-api.com/v6/" + chave + "/latest/" + moedas;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();


        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeRate.class);
        } catch (Exception e) {
            throw new RuntimeException("Moeda Inválida");
        }
    }

    private ExchangeRate parseExchangeRate(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String baseCode = jsonObject.get("base_code").getAsString();
        Map<String, Double> filter = filterRates(jsonObject.getAsJsonObject("conversion_rates"));
        return new ExchangeRate(baseCode, filter);
    }

    private Map<String, Double> filterRates(JsonObject conversionRates) {
        Map<String, Double> filterRates = new HashMap<>();
        for (String currency : new String[]{"USD", "EUR", "ARS", "COP", "GBP", "CLP"}) {
            if (conversionRates.has(currency)) {
                filterRates.put(currency, conversionRates.get(currency).getAsDouble());
            }

        }
        return filterRates;
    }
}

