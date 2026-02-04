package model.entities.services;

import model.entities.AluguelDeCarro;
import model.entities.Fatura;

import java.time.Duration;

public class ServicoDeAluguel {

    private double precoPorHora;
    private double precoPorDia;

    private ServicoDeImposto servicoDeImposto;

    public ServicoDeAluguel(double precoPorHora, double precoPorDia, ServicoDeImpostoBrasil ServicoDeImposto) {
        this.precoPorHora = precoPorHora;
        this.precoPorDia = precoPorDia;
        this.servicoDeImposto = ServicoDeImposto;
    }

    public static void processFatura(AluguelDeCarro ac) {
    }

    public void processamentoDaFatura(AluguelDeCarro AluguelDeCarro){

        double minutos = Duration.between(AluguelDeCarro.getComeco(), AluguelDeCarro.getFim()).toMinutes();
        double horas = minutos / 60.0;

        double pagamentoBasico;
        if (horas <= 12.0){
            pagamentoBasico = precoPorHora * Math.ceil(horas);
        }
        else {
            pagamentoBasico = precoPorDia * Math.ceil(horas / 24.0);
        }

        double imposto = servicoDeImposto.imposto(pagamentoBasico);

        AluguelDeCarro.setFatura(new Fatura(pagamentoBasico, imposto));
    }
}