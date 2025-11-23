package com.espaco.controle.classes;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

@Data // Gera Getters, Setters, toString, etc.
@NoArgsConstructor // Construtor vazio obrigatório para Nitrite
@Indices({
    @Index(value = "nome", type = IndexType.Unique), // Não permite nomes repetidos
    @Index(value = "dataLancamento", type = IndexType.NonUnique)
})
public class Missao implements Serializable {

    @Id
    private Long idMissao;
    
    private String nome;
    private String destino;
    private String dataLancamento; // Usando String para simplificar compatibilidade
    private String dataRetorno;
    private boolean concluida;
    
    // Construtor personalizado
    public Missao(String nome, String dataLancamento, String dataRetorno) {
        this.nome = nome;
        this.destino = "Lua";
        this.dataLancamento = dataLancamento;
        this.concluida = false;
        this.dataRetorno = dataRetorno;
    }
}