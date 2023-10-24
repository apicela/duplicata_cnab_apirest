package apicela.duplicata.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rodape {
    private String identificacaoTipoRegistro = "003";
    private String espacoReservado;
}
