*** Settings ***
Resource    ../main.robot

*** Variables ***
&{vendas} 
...    PAGE_TITULO=//h1[contains(., "Clientes")]
...    URL=//http://localhost:3000/vendas
...    CLIENTE=//301
...    PRODUTO=//4580287676267
...    FINALIZAR=//9000
...    KEY_ENTER=//Enter
...    PAGE_TITULO=//h1[contains(., "Vendas")]
