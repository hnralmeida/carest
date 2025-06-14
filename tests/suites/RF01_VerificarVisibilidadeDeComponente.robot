*** Settings ***
Resource    ../resources/main.robot
Test Setup    Dado que eu acesse o Carest
Test Teardown    Fechar navegador

*** Test Cases ***
# RF01.01 
Apenas acessar o Home 
    [Tags]    RF01_01
    Acessar Home

#RF01.02
Acessar o Home e verificar se um componente X está visível
    [Tags]    RF01_02
    Acessar Home
    Verificar Visibilidade de um componente