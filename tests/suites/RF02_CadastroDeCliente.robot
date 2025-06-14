*** Settings ***
Resource    ../resources/main.robot
Test Setup  Dado que eu acesse o Carest
Test Teardown    Fechar navegador

# RF02.01 
*** Test Cases ***
Adicionar Cliente Com Sucesso
    Go To    ${URL}/cliente
    Wait Until Element Is Visible    ${cliente.PAGE_TITULO}    timeout=5s
    Click Element    ${cliente.BTN_ADICIONAR}
    Input Text    ${cliente.INPUT_NOME}    João Teste
    Input Text    ${cliente.INPUT_TELEFONE}    11999999999
    Input Text    ${cliente.INPUT_EMAIL}    joao@teste.com
    Input Text    ${cliente.INPUT_CODIGO}    12345
    Input Text    ${cliente.INPUT_NASCIMENTO}    2000-01-01
    Click Button    ${cliente.BTN_SALVAR}
    Page Should Contain    Cliente adicionado com sucesso

    