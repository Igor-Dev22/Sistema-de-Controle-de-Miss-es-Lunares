
package com.espaco.controle.classes;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.objects.Id;

@Data
@NoArgsConstructor
public class Astronauta implements Serializable {
    @Id
    private Long id;
    private String nome;
    private String especialidade; // Ex: Piloto, Biólogo
    
    public Astronauta(String nome, String especialidade) {
        this.id = System.nanoTime(); // Gera ID único automaticamente
        this.nome = nome;
        this.especialidade = especialidade;
    }
}