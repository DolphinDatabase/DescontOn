package com.dev.ecommerce.utils;

import java.util.List;

import com.dev.ecommerce.dto.SacolaDTO;

public class Util {

    String quantidadeMaior = "[ProdutoQuantidade >]";
    String quantidadeMenor = "[ProdutoQuantidade <]";
    String quantidadeIgual = "[ProdutoQuantidade =]";
    String produtoValorMaior = "[ProdutoValor >]";
    String produtoValorMenor = "[ProdutoValor <]";
    String produtoValorIgual = "[ProdutoValor =]";
    
    public Double converterDouble(String valor){
        String recebe = valor.replaceAll("[\\D]", "");
        
        return Double.parseDouble(recebe);
    }

    public Integer converterInteger(String valor){
        String recebe = valor.replaceAll("[\\D]", "");
        return Integer.parseInt(recebe);
    }

    public Double buscarValor(String condicao){
        String[] output = condicao.split("&&");
        String valor = output[0];

        if(valor.contains(quantidadeMaior) || valor.contains(quantidadeMenor) || valor.contains(quantidadeIgual)){
            valor = output[1];
        }       

        return converterDouble(valor);
    }

    public Integer buscarQuantidade(String condicao){
        String[] output = condicao.split("&&");
        String quantidade = output[1];

        if(quantidade.contains(produtoValorMaior) || quantidade.contains(produtoValorMenor) || quantidade.contains(produtoValorIgual)){
            quantidade = output[0];
        }        

        return converterInteger(quantidade);
    }

    public double somarQuantidade(List<SacolaDTO> lista){
        Double qtd = 0.0;
        for(SacolaDTO item:lista){
            qtd += item.getQuantidade();
        }
        return qtd;
    }
}
