package apicela.duplicata.utils;


import java.util.Random;

public class TransacaoGenerator {
    public static final String PREFIX = "002";

    Random random = new Random();

    char gerarTipoTransacao() {
        int number = random.nextInt(3);
        if (number == 0) {
            return 'C';
        } else if (number == 1) {
            return 'D';
        } else return 'T';
    }
}
