package com.example;


import com.example.util.Criptografar;
import com.example.util.Descriptografar;
import com.example.util.RSAKey;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.util.RSA.*;

public class Main {


    public static void main(String[] args) {
        String mensagemOriginal = "Vai";

        try {
            Path path = Paths.get("src/main/resources/arquivo.txt");
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, StandardCharsets.UTF_8);
            mensagemOriginal = content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Criptografar.criptografar(mensagemOriginal);
        Descriptografar.descriptografar();

        final String mensagemDescriptografada = RSAKey.readTextFileToString(arquivoDescriptografado);
        final String mensagemCriptografada = RSAKey.readTextFileToString(arquivoCriptografado);

        printarMensagens(mensagemOriginal, mensagemCriptografada, mensagemDescriptografada);
    }

    private static void printarMensagens(String mensagemOriginal, String mensagemCriptografada, String mensagemDescriptografada) {
        System.out.println("Mensagem Original: " + mensagemOriginal);
        System.out.println("Mensagem Criptografada: " + mensagemCriptografada);
        System.out.println("Mensagem Decriptografada: " + mensagemDescriptografada);
    }
}
