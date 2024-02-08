package com.dcr.api.validator;

import java.lang.reflect.Field;

public class Validator {

	public static void validarTamanhos(Object objeto) throws IllegalAccessException {
        Class<?> classe = objeto.getClass();

        for (Field campo : classe.getDeclaredFields()) {
           
            if (campo.isAnnotationPresent(TamanhoMaximo.class)) {
                TamanhoMaximo anotacaoMaxima = campo.getAnnotation(TamanhoMaximo.class);
                int tamanhoMaximo = anotacaoMaxima.value();

                campo.setAccessible(true);
                campo.getType();
                if(campo.getType().getName().equals("java.lang.String")) {
                	 String valorCampo = (String) campo.get(objeto);

                     if (valorCampo != null && valorCampo.length() > tamanhoMaximo) {
                    	 throw new IllegalArgumentException("Tamanho máximo excedido para o campo " + campo.getName() + ", o tamanho máximo é: " + tamanhoMaximo );
                     }
                }
                else{
                	Object valorCampo = campo.get(objeto);

                    if (valorCampo != null && valorCampo.toString().length() > tamanhoMaximo) {
                    	throw new IllegalArgumentException("Tamanho máximo excedido para o campo " + campo.getName() + ", o tamanho máximo é: " + tamanhoMaximo );
                    }
               }
                
               
            }
            
            if (campo.isAnnotationPresent(TamanhoMinimo.class)) {
            	TamanhoMinimo anotacaoMinima = campo.getAnnotation(TamanhoMinimo.class);
                int tamanhoMinimo = anotacaoMinima.value();

                campo.setAccessible(true);
                campo.getType();
                if(campo.getType().getName().equals("java.lang.String")) {
                	 String valorCampo = (String) campo.get(objeto);

                     if (valorCampo != null && valorCampo.length() < tamanhoMinimo) {
                    	 throw new IllegalArgumentException("Tamanho inválido para o campo " + campo.getName() + ", o tamanho mínimo é: " + tamanhoMinimo );
                     }
                }
                else{
                	Object valorCampo = campo.get(objeto);

                    if (valorCampo != null && valorCampo.toString().length() < tamanhoMinimo) {
                    	throw new IllegalArgumentException("Tamanho inválido para o campo " + campo.getName() + ", o tamanho mínimo é: " + tamanhoMinimo );
                    }
               }
                
               
            }
        }
    }
}
