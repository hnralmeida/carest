*** Settings ***
Resource    ../resources/main.robot
Test Setup  Dado que eu acesse o Carest
Test Teardown    Fechar navegador

*** Test Cases ***
Fluxo Completo de Venda com Cliente, Produto e Registro
    Go To    ${URL}/vendas
    Maximize Browser Window

    # Aguarda algum elemento visível para garantir que a tela carregou
    Wait Until Page Contains Element    xpath://h1[contains(., "Cliente")]

    # Digita código do cliente e pressiona Enter
    Press Keys    xpath=//body    ${vendas.CLIENTE}
    Sleep    1s
    Press Keys    xpath=//body    ${vendas.KEY_ENTER}

    # Digita código do produto e pressiona Enter
    Press Keys    xpath=//body    ${vendas.PRODUTO}
    Sleep    1s
    Press Keys    xpath=//body    ${vendas.KEY_ENTER}

    # Digita código 9000 para registrar venda
    Press Keys    xpath=//body    ${vendas.FINALIZAR}
    Sleep    1s
    Press Keys    xpath=//body    ${vendas.KEY_ENTER}

    [Teardown]    Close Browser
