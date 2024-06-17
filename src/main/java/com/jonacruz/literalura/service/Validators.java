package com.jonacruz.literalura.service;

public class Validators {

    public static boolean validateRange(int index, int firstNum, int lastNum){

        if ((index >= firstNum) && (index <= lastNum)) {
            return true;
        }
        System.out.println("\nIngrese un numero de los mostrados en la pantalla\n");
        return false;
    }

    public static boolean validateInt(String data){

        try {
            Integer.valueOf(data);
            return true;
        } catch (NumberFormatException e){
            return false;
        }

    }
}
