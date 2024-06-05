package br.com.alura.litealura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
