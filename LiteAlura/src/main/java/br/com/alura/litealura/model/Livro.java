package br.com.alura.litealura.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer downloads;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;

public Livro(){}

    public Livro(LivroDados livroDados){
    this.titulo = livroDados.titulo();
    this.idiomas = Idiomas.fromString(livroDados.idiomas().split(",")[0].trim());
    this.downloads = livroDados.download();
    this.autor =livroDados.autor();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return ", titulo='" + titulo + '\'' +
                ", downloads=" + downloads +
                ", autor='" + autor + '\'' +
                ", idiomas=" + idiomas ;
    }
}
