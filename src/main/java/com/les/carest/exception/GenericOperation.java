package com.les.carest.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        responses = {
                @ApiResponse(responseCode = "200", description = "Caso seja encontrado com sucesso."),
                @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
                @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
        }
)
public @interface GenericOperation {
    String description();
}