package com.espaco.controle.classes;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.objects.Id;

@Data
@NoArgsConstructor
public class Resultado implements Serializable {
    @Id
    private Long id;
    private String titulo;
    private String descricao;
    private String nomeMissaoVinculada; // Para saber de qual missão veio
    
    public Resultado(String titulo, String descricao, String nomeMissaoVinculada) {
        this.id = System.nanoTime();
        this.titulo = titulo;
        this.descricao = descricao;
        this.nomeMissaoVinculada = nomeMissaoVinculada;
    }
}