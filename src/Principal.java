import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        ConsultaMoedas novaConsultaMoedas = new ConsultaMoedas();
        Scanner leitura = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    =================================
                   |      CONVERSOR DE MOEDAS        |
                    =================================
                    1 - Converter moeda
                    2 - Sair
                   """
            );
            System.out.println("Escolha uma das opções");
            int escolha = leitura.nextInt();
            leitura.nextLine();

            switch (escolha) {
                case 1 -> {
                    ConverterMoeda.converterMoeda();
                    System.out.println("Escolha uma Operação: ");
                    int conversao = leitura.nextInt();

                    if (conversao == 1){
                        converterMoeda(novaConsultaMoedas, "USD");
                    } else if (conversao == 2) {
                        converterMoeda(novaConsultaMoedas, "EUR");
                    } else if (conversao == 3) {
                        converterMoeda(novaConsultaMoedas, "ARS");
                    } else if (conversao == 4) {
                        converterMoeda(novaConsultaMoedas, "COP");
                    } else if (conversao == 5) {
                        converterMoeda(novaConsultaMoedas, "GBR");
                    } else if (conversao == 6) {
                        converterMoeda(novaConsultaMoedas, "CLP");
                    } else {
                        System.out.println("Voltando para o menu principal");
                        continue;
                    }
                }

                case 2 -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida1");
            }
        }



    }

    private static void converterMoeda(ConsultaMoedas consultaMoedas, String moedaDestino) {
        ExchangeRate consultaMoeda = consultaMoedas.consultaValor("BRL");

        if (consultaMoeda != null && consultaMoeda.conversionRates() != null){
            Double taxaDeCambio = (Double) consultaMoeda.conversion_rates().get(moedaDestino);

            if (taxaDeCambio != null) {
                double valorReais;
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o valor que deseja converter: ");
                valorReais = scanner.nextDouble();

                double valorConvertido = valorReais * taxaDeCambio;
                System.out.println("O valor de " + valorReais + " BRL" + " Convertido para " + moedaDestino + " é de: " + valorConvertido);
                System.out.println("=======================================");
            } else {
                System.out.println("Taxa de câmbio para " + moedaDestino + "não disponivel.");
                System.out.println("=======================================");
            }
        } else {
            System.out.println("Erro ao obter as taxas de câmbio.");
            System.out.println("=======================================");
        }
    }
}