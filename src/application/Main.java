import model.entities.AluguelDeCarro;
import model.entities.Veiculo;
import model.entities.services.ServicoDeAluguel;
import model.entities.services.ServicoDeImpostoBrasil;

void main() {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm)");

    System.out.println("Entre com os dados do aluguel: ");
    System.out.print("Modelo do carro: ");
    String modeloDoCarro = sc.nextLine();
    System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
    LocalDateTime comeco = LocalDateTime.parse(sc.nextLine(), fmt);
    System.out.print("Devolucao (dd/MM/yyyy hh:mm): ");
    LocalDateTime fim = LocalDateTime.parse(sc.nextLine(), fmt);

    AluguelDeCarro ac = new AluguelDeCarro(comeco, fim, new Veiculo(modeloDoCarro));

    System.out.print("Entre com o preco por hora: ");
    double precoPorHora = sc.nextDouble();
    System.out.print("Entre com o preco por dia: ");
    double precoPorDia = sc.nextDouble();

    ServicoDeAluguel sa = new ServicoDeAluguel(precoPorHora, precoPorDia, new ServicoDeImpostoBrasil());

    ServicoDeAluguel.processFatura(ac);

    System.out.println("Fatura: ");
    System.out.print("Pagamento basico: " + ac.getFatura().getPagamentoBasico());
    System.out.print("Imposto: " + ac.getFatura().getImposto());
    System.out.print("Pagamento total: " + ac.getFatura().getPagamentoTota());

    sc.close();
}