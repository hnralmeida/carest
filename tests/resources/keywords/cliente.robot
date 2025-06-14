*** Settings ***
Resource    ../main.robot


*** Keywords ***

#KEYWORDS PREENCHIMENTO CORRETO
Acessar Clientes    
    Wait Until Element Is Visible    ${home.BTN_PAG_INICIAL}    5s
    Click Element                    ${home.BTN_PAG_INICIAL}