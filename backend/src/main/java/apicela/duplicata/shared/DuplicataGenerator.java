package apicela.duplicata.shared;

import java.util.ArrayList;
import java.util.List;

public class DuplicataGenerator {

    public List<String> criarDuplicataValida() {
        List<String> duplicata = new ArrayList<>();
        duplicata.add(CommonStrings.CACABECALHO_HOME_DEVELOPER);
        duplicata.add(CommonStrings.TRANSACAO_OK_01);
        duplicata.add(CommonStrings.TRANSACAO_OK_02);
        duplicata.add(CommonStrings.TRANSACAO_OK_03);
        duplicata.add(CommonStrings.RODAPE_VALIDO);
        return duplicata;
    }
}
