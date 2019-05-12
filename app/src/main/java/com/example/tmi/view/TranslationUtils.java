package com.example.tmi.view;

public class TranslationUtils {

    public static String translate(String name) {
        switch(name) {
            case "neutral_mood":
                return "Neutral";
            case "anger":
                return "Enfado";
            case "disgusted":
                return "Disgusto";
            case "fear":
                return "Miedo";
            case "happiness":
                return "Felicidad";
            case "sadness":
                return "Tristeza";
            case "surprise":
                return "Sorpresa";
        }
        return "";
    }
}
