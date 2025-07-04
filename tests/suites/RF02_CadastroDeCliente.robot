*** Settings ***
Resource    ../resources/main.robot
Test Setup  Dado que eu acesse o Carest
Test Teardown    Fechar navegador

# RF02.01 
*** Test Cases ***
Adicionar Cliente Com Sucesso
    Go To    ${URL}/produto
    Wait Until Element Is Visible    ${cliente.PAGE_TITULO}    timeout=5s
    Click Element    ${cliente.BTN_ADICIONAR}
    Input Text    ${cliente.INPUT_NOME}    João Teste
    Input Text    ${cliente.INPUT_VALOR}    4.00
    Input Text    ${cliente.INPUT_CUSTO}    3.00
    Input Text    ${cliente.INPUT_CODIGO}    12345
    Click Button    ${cliente.BTN_SALVAR}
    Page Should Contain    Produto adicionado com sucesso

    