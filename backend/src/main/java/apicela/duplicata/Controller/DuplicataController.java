package apicela.duplicata.Controller;

import apicela.duplicata.Services.DuplicataService;
import apicela.duplicata.projections.DuplicataProjectionImpl;
import apicela.duplicata.shared.LeitorDeArquivo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/duplicatas")
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
public class DuplicataController {
    public static final String ENDPOINT = "/duplicatas";

    final DuplicataService duplicataService;
    final LeitorDeArquivo leitorDeArquivo;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "CRIAR DUPLICATA", description = "Insira um arquivo padrões CNAB no formato TXT ou PDF válido.")
    public ResponseEntity<Object> uploadCNAB(@Parameter(description = "Insira um arquivo de texto.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
                                             @RequestPart(required = true) MultipartFile file) throws Exception {
        List<String> linhasArquivo = leitorDeArquivo.read(file);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Arquivo CNAB enviado e processado com sucesso.");
        response.put("duplicata", duplicataService.validarDuplicata(linhasArquivo, file.getOriginalFilename()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation(summary = "LISTAR DUPLICATAS", description = "Lista todas duplicatas existentes no banco de dados.")
    public ResponseEntity<Object> listarTodasDuplicatas() {
        return ResponseEntity.status(HttpStatus.OK).body(duplicataService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "BUSCAR DUPLICATA ATRAVÉS DO ID", description = "Lista duplicata baseado no ID da duplicata.")
    public ResponseEntity<Object> getDuplicataById(@PathVariable(value = "id") Long id) throws IllegalAccessException {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID.");
        }
        Optional<DuplicataProjectionImpl> duplicataOptional = duplicataService.findById(id);
        if (duplicataOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(duplicataService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(duplicataOptional.get());
    }

}

