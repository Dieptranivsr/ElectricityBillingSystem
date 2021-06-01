/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Dieptuantran
 */
public class GenerateRandomCode {
        /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;

    public GenerateRandomCode(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public GenerateRandomCode(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public GenerateRandomCode(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public GenerateRandomCode() {
        this(21);
    }
    
    public static void main(String []args){
        System.out.println("Hello World");
        //GenerateRandomCode gen = new GenerateRandomCode(8, ThreadLocalRandom.current());
        //GenerateRandomCode gen = new GenerateRandomCode(8);
        //System.out.println(gen);
        //GenerateRandomCode session = new GenerateRandomCode();
        //System.out.println(session);
        String easy = GenerateRandomCode.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
        GenerateRandomCode tickets = new GenerateRandomCode(23, new SecureRandom(), easy);
        System.out.println(tickets);
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        System.out.println(number);
    }
}
