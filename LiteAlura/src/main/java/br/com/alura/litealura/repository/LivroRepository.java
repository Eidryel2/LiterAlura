package br.com.alura.litealura.repository;

import br.com.alura.litealura.model.Idiomas;
import br.com.alura.litealura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository  extends JpaRepository<Livro, Long> {
    Optional<Livro>findByTituloContainIgnoreCase(String nomeLivro);

    List<Livro>findByAtoresContainingIgnoreCase(String nomeAutor);

    List<Livro>findByIdioma(Idiomas idiomas);
}
