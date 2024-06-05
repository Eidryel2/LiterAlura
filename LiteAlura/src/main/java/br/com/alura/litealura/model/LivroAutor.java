package br.com.alura.litealura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record LivroAutor(@JsonAlias("name") String nome,
                         @JsonAlias("birth_year") Integer nascimento,
                         @JsonAlias("death_year") Integer falecimento) {
}
