package com.stockhubpro_api.dto;

public record LoginResponse(	//o que o backend devolve no login
        String token,
        String nome,
        String nomeEmpresa
) {
}

/*
 * devolve, além do token (o crachá), o nome do usuário e da empresa, 
 * para o app poder mostrar “Olá, Júlio” ou o nome da empresa na tela inicial sem fazer outro pedido.
 * */