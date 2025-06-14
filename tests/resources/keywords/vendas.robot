*** Settings ***
Resource    ../main.robot

*** Keywords ***
Acessar Vendas
    [Arguments]    ${url}=${URL}
    Go To    ${url}
    Wait Until Element Is Visible    ${vendas.PAGE_TITULO}    timeout=5s
