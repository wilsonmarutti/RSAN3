package com.example.util;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.example.util.RSA.*;
import static com.example.util.RSAKey.readTextFileToString;


@UtilityClass
public class Criptografar {
    public static void criptografar(String texto) {
        String textFileEncoded = Base64.getEncoder().encodeToString(texto.getBytes());
        int blockSize = TextChunk.blockSize(getModulus());

        List<BigInteger> bigIntegerList = new ArrayList<>();
        for (String chunk : TextChunk.splitChunk(textFileEncoded, blockSize)) {

            BigInteger originalChunk = new BigInteger(chunk.getBytes());
            BigInteger encodedChunk = originalChunk
                    .modPow(new BigInteger(readTextFileToString(chavePublica)), getModulus());

            bigIntegerList.add(encodedChunk);
        }

        writeTextFile(bigIntegerList);
    }
}
