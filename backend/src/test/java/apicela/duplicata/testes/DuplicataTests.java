package apicela.duplicata.testes;


import apicela.duplicata.Controller.DuplicataController;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    @DisplayName("Criar duplicata com service")
    void criarDuplicataService() throws Exception {
        var duplicata = duplicataGenerator.criarDuplicataValida();
        duplicataService.validarDuplicata(duplicata, "nomeArquivo");
        int lastIndex = duplicataRepository.findAll().size();
        var ultimaDuplicata = duplicataRepository.findAll().get(lastIndex - 1);
        log.info("{}", ultimaDuplicata);
    }

    @Test
    @Order(1)
    @DisplayName("Criar duplicata com chamada http")
    void uploadCNAB_retornarOk() throws Exception {
        String conteudo = "001HOME DEVELOPER________________123456789_____MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "002C80309___________123456__________777333__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "002D50000099________123456789_______992224498_______MMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "003MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM";
        MockMultipartFile file = new MockMultipartFile("file", "name.txt", "text/plain", conteudo.getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart(DuplicataController.ENDPOINT)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("bad request por metodo http")
    void uploadCNAB_retornarBadRequest() throws Exception {
        String conteudo = "001HOME DEVELOPER________________123456789_____MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "002C80309___________1234K6__________777333__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "002D50000099________123456789_______992224498_______MMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "003MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM";
        MockMultipartFile file = new MockMultipartFile("file", "name.txt", "text/plain", conteudo.getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart(DuplicataController.ENDPOINT)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
