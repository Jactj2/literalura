package com.jonacruz.literalura.service;

public class PrinterEspecial {

    public static void erroPrint(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
}
