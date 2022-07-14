package com.example.util;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.util.Base64;
import java.util.List;

import static com.example.util.RSA.*;
import static com.example.util.RSAKey.getPrivatekey;

@UtilityClass
public class Descriptografar {

    public static void descriptografar() {
        List<String> destTextList = readTextFileToList();

        StringBuilder base64 = new StringBuilder();
        for (String textLine : destTextList) {
            BigInteger encodedChunk = new BigInteger(textLine);
            BigInteger originalChunk = encodedChunk.modPow(getPrivatekey(), getModulus());

            TextChunk chunk2 = new TextChunk(originalChunk);

            for (int i = chunk2.toString().length(); i > 0; i--) {
                base64.append(chunk2.toString().substring(i - 1, i));
            }
        }

        byte[] bytes = Base64.getDecoder().decode(base64.toString().getBytes());

        String textDecrypt = new String(bytes);
        writeDecryptTextFile(textDecrypt);
    }
}
