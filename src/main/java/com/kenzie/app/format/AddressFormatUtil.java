package com.kenzie.app.format;

import java.sql.SQLOutput;
import java.util.HashMap;

public class AddressFormatUtil {

    private static HashMap<String, String> codeTable = new HashMap<>();

    public static void initCodeTable() {
        codeTable.put("ST", "STREET");
        codeTable.put("RD", "ROAD");
        codeTable.put("DR", "DRIVE");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("LN", "LANE");
        codeTable.put("PL", "PLACE");
        codeTable.put("CT", "COURT");
        codeTable.put("CIR", "CIRCLE");
        codeTable.put("BLVD", "BOULEVARD");
        codeTable.put("PKWY", "PARKWAY");
        codeTable.put("HWY", "HIGHWAY");
        codeTable.put("TER", "TERRACE");
        codeTable.put("WAY", "WAY");
        codeTable.put("TRCE", "TRACE");
    }
    public static String replaceAbbreviation(String street) {
        // ref https://www.tutorialspoint.com/posix-character-classes-p-punct-java-regex
        street = street.replaceAll("\\p{Punct}", "").toUpperCase();
        String[] words = street.split(" ");
        String newStreet = "";

        for (String word : words) {
            if (codeTable.containsKey(word)) {
                newStreet += codeTable.get(word) + " ";
            } else {
                newStreet += word + " ";
            }
        }
        return newStreet.trim();
    }

    public static void main(String[] args) {
        String street = "123 Main St.";
        System.out.println(replaceAbbreviation(street));
    }
}
