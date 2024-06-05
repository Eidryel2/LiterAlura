package br.com.alura.litealura.model;

public enum Idiomas {
    EN("Ingles", "Ingles"),
    PT("Portugues", "Portugues"),
    ES("Espanhol", "Espanhol");

    private String idiomasOmdb;
    private String idiomasPortugues;

    Idiomas(String categoriaOmdb, String categoriaPortugues){
        this.idiomasOmdb = categoriaOmdb;
        this.idiomasPortugues = categoriaPortugues;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idiomasOmdb.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Idiomas fromPortugues(String text) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idiomasPortugues.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
