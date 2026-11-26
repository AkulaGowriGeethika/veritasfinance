package com.veritasfinance.ai;

public class GeminiAPIService {

    private GeminiService service = new GeminiService();

    public String generateAdvice(int score) {

        return service.getAdvice(score);

    }

}