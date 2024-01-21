package org.example.rest;

import com.sun.tools.javac.util.DefinedBy;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(String mensagemError){
        this.erros = Arrays.asList(mensagemError);
    }
}
