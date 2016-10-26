package com.keggphones.Security;

/**
 * Created by mm on 19/10/2016.
 */
public class Encryption {

    private String alphabet;

    private char[] alphabetChar;

    public Encryption()
    {
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST"
                + "UVWXYZ1234567890,.:;/-_%!¿?$#[]{} ";
        this.alphabetChar = alphabet.toCharArray();
    }

    public String encrypt(String text, String key)
    {
        String newText = "";

        char[] textChar = text.toCharArray();
        char[] keyChar = key.toCharArray();
        int k = 0;
        int keyPosition = 0;
        int letterPosition = 0;
        int newPosition = 0;
        for(int i = 0; i < text.length(); i++)
        {
            letterPosition = findLetter(textChar[i]);
            keyPosition = findLetter(keyChar[k]);
            newPosition = letterPosition + keyPosition;
            if(newPosition > alphabet.length())
            {
                newPosition = newPosition - alphabet.length();
            }
            newText += alphabetChar[newPosition-1];
            k++;
            if (k == key.length())
            {
                k = 0;
            }
        }

        return newText;
    }

    public String decrypting(String text, String key)
    {
        String newText = "";

        char[] textChar = text.toCharArray();
        char[] keyChar = key.toCharArray();
        int k = 0;
        int keyPosition = 0;
        int letterPosition = 0;
        int newPosition = 0;
        for (int i = 0; i < text.length(); i++)
        {
            letterPosition = findLetter(textChar[i]);
            keyPosition = findLetter(keyChar[k]);
            newPosition = letterPosition - keyPosition;
            if (newPosition < 1)
            {
                newPosition = alphabet.length()+ newPosition;
            }
            newText += alphabetChar[newPosition - 1];
            k++;
            if (k == key.length())
            {
                k = 0;
            }
        }

        return newText;
    }

    private int findLetter(char letter)
    {
        int pos = 0;

        for(int i = 0; i < this.alphabet.length(); i++)
        {
            if(letter == alphabetChar[i])
            {
                pos = i + 1;
                i = this.alphabet.length();
            }
        }

        return pos;
    }

}
