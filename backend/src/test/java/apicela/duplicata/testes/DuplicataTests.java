package apicela.duplicata.testes;


import apicela.duplicata.Services.DuplicataService;
import apicela.duplicata.repositories.DuplicataRepository;
import apicela.duplicata.utils.DuplicataGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DuplicataTests {
    private static final Logger log = LoggerFactory.getLogger(DuplicataTests.class);
    private final DuplicataGenerator duplicataGenerator = new DuplicataGenerator();
    @Autowired
    DuplicataService duplicataService;
    @Autowired
    DuplicataRepository duplicataRepository;
    @Autowired
    private MockMvc mvc;

    @Test
    @Order(0)
    @DisplayName("")
    void criarDuplicataService() throws Exception {
        var duplicata = duplicataGenerator.criarDuplicataValida();
        duplicataService.validarDuplicata(duplicata, "nomeArquivo");
        int lastIndex = duplicataRepository.findAll().size();
        var ultimaDuplicata = duplicataRepository.findAll().get(lastIndex - 1);
        log.info("{}", ultimaDuplicata);
    }


}
