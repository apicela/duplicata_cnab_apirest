package apicela.duplicata.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Duplicata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @OneToOne
    @JsonIgnore
    private Cabecalho cabecalho;
    @Embedded
    @JsonIgnore
    private Rodape rodape;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Transacoes> transacoes;
}