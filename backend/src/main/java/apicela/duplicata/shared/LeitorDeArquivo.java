package apicela.duplicata.shared;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class LeitorDeArquivo {


    public List<String> read(MultipartFile file) throws Exception {
        List<String> linhasArquivo = null;
        if (file.getContentType().equals("text/plain")) {
            return linhasArquivo = readTXT(file);
        } else if (file.getContentType().equals("application/pdf")) {
            return linhasArquivo = readPDF(file);
        } else {
            log.info("Erro na leitura do arquivo {}, formato não reconhecido pelo sistema.", file.getOriginalFilename());
            throw new IllegalArgumentException("O arquivo no formato '" + file.getContentType() + "' não é permitido. Favor inserir documento no formato PDF ou TXT.");
        }
    }

    public List<String> readPDF(MultipartFile file) throws Exception {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        List<String> lines = Arrays.asList(text.split("\n"));
        return lines;
    }

    public List<String> readTXT(MultipartFile file) throws Exception {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
