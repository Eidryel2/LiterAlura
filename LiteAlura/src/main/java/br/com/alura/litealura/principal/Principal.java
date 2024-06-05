package br.com.alura.litealura.principal;

import br.com.alura.litealura.model.*;
import br.com.alura.litealura.repository.LivroRepository;
import br.com.alura.litealura.service.ConsumoApi;
import br.com.alura.litealura.service.ConverteDados;

import java.util.*;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/";

    private List<LivroDados> livrosDados = new ArrayList<>();
    private LivroRepository repository;
    private List<Livro> livro = new ArrayList<>();

    public Principal(LivroRepository repository) {this.repository =repository;}


    public void exibeMenu(){
    var opcao = -1;
    while (opcao !=0){
        var menu = """
                ******************* Bem Vindo ao LiteAlura *******************
                Selecione uma opção: 

                1 - Buscar livro por título 
                2 - Listar livros registrados 
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros por idioma
                6 - SAIR
                
                """;

            System.out.println(menu);
            System.out.print("Escolha o número da sua opção: ");
            leitura.nextLine();



            switch (opcao) {
                case 1:
                    buscarLivroTitulo();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
//                case 4:
//                    buscarAutoresVivos();
//                    break;
                case 5:
                    listarLivrosIdiomas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }
    }

    public void buscarLivroTitulo(){
        LivroDados dados = getLivrosDados();
        Livro livro = new Livro(dados);
        repository.save(livro);
        System.out.println(dados);
        }

    private LivroDados getLivrosDados() {
        System.out.println("Digite o nome do livro para busca");
        String nomeLivro = leitura.nextLine();
        String json = consumo.obterDados(ENDERECO + "?search="
                + nomeLivro.replace(" ", "%20"));;
        System.out.println("Corpo da resposta da API" + json);

        LivroDados dados = conversor.obterDados(json, LivroDados.class);
        System.out.println("JSON recebido: " + json);
        return dados;
    }

    private void buscarLivrosRegistrados(){
        livro = repository.findAll();
        livro.stream()
                .sorted(Comparator.comparing(Livro::getIdiomas))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados(){
        System.out.println("Qual o nome do Autor?");
        var nomeAutor = leitura.nextLine();
        List<Livro> seriesEncontradas = repository.findByAtoresContainingIgnoreCase(nomeAutor);
        System.out.println("Livros em que " + nomeAutor + " trabalhou: ");
        seriesEncontradas.forEach(s ->
                System.out.println(s.getTitulo() ));
    }

    private void listarLivrosIdiomas(){
        System.out.println("Deseja buscar Livros por qual idioma? ");
        var nomeIdiomas = leitura.nextLine();
        Idiomas idiomas = Idiomas.fromPortugues(nomeIdiomas);
        List<Livro> livrosIdiomas = repository.findByIdioma(idiomas);
        System.out.println("Livros com o  " + nomeIdiomas);
        livrosIdiomas.forEach(System.out::println);
    }

}
