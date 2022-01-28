package br.ifgoiano.sanbrasil.novoramo.testes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Testes {

    public static void main(String[] args) {
      var dataInicial =  LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0));

      var dataFinal = LocalDateTime.now();

      var data= ChronoUnit.MINUTES.between(dataInicial, dataFinal);

        System.out.println(data);

        System.out.println(Math.floor(100000 + Math.random() * 900000));

    }
}
