package apicela.duplicata.Services;

import apicela.duplicata.shared.CommonStrings;
import apicela.duplicata.shared.DuplicataGenerator;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DuplicataService duplicataService;
    private final DuplicataGenerator duplicataGenerator = new DuplicataGenerator();

    @Override
    public void run(String... args) throws Exception {
        var duplicata = duplicataGenerator.criarDuplicataValida();
        duplicataService.validarDuplicata(duplicata, "name");

        List<String> duplicata2 = new ArrayList<>();
        duplicata2.add("001JAMIL APICELA_________________31995085792___MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        duplicata2.add(CommonStrings.TRANSACAO_OK_01);
        duplicata2.add(CommonStrings.TRANSACAO_OK_03);
        duplicata2.add(CommonStrings.TRANSACAO_OK_02);
        duplicata2.add(CommonStrings.TRANSACAO_OK_01);
        duplicata2.add(CommonStrings.RODAPE_VALIDO);
        duplicataService.validarDuplicata(duplicata2, "name2");

        List<String> duplicata4 = new ArrayList<>();
        duplicata4.add("001JHIN APICELA___________________4444_________MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        duplicata4.add("002C444444__________4444_________4444_______________MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        duplicata4.add("002D444444__________4444_________4444_______________MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        duplicata4.add("002D444444__________4444_________4444_______________MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        duplicata4.add("002T444444__________4444_________4444_______________MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        duplicata4.add(CommonStrings.RODAPE_VALIDO);
        duplicataService.validarDuplicata(duplicata4, "name4");

    }
}
