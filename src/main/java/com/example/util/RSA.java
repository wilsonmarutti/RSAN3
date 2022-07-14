package com.example.util;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.example.util.RSAKey.*;


@UtilityClass
public class RSA {

    public static final String chavePublica = "src/main/resources/publicKey.txt";
    public static final String primeList = "src/main/resources/primeList.txt";
    public static final String arquivoCriptografado = "src/main/resources/textEncrypt.txt";
    public static final String arquivoDescriptografado = "src/main/resources/textDecrypt.txt";



    static void writeTextFile(List<BigInteger> bigIntegerList) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoCriptografado));
            for (BigInteger B : bigIntegerList) {
                bw.write(B + "\n");
            }
            bw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BigInteger getModulus() {

        List<String> numberPrimesList = readTwoPrimeNumbers();

        BigInteger p = new BigInteger(numberPrimesList.get(0));
        BigInteger q = new BigInteger(numberPrimesList.get(1));

        return p.multiply(q);
    }



    static void writeDecryptTextFile(String text) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDescriptografado));
            bw.write(text);
            bw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static List<String> readTextFileToList() {
        List<String> textList = new ArrayList<>();

        try {
            InputStream file = new FileInputStream(arquivoCriptografado);

            Reader isr = new InputStreamReader(file);
            final BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                textList.add(line);
            }

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

        return textList;
    }

}
