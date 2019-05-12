package com.example.tmi.view;

public class TranslationUtils {

    public static String translate(String name) {
        switch(name) {
            case "neutral":
                return "Neutral";
            case "angry":
                return "Enfadado";
            case "disgusted":
                return "Disgustado";
            case "scared":
                return "Asustado";
            case "happy":
                return "Feliz";
            case "sad":
                return "Triste";
            case "surprised":
                return "Sorprendido";
        }
        return "";
    }
}
