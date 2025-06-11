*** Settings ***
Resource    ../main.robot


*** Keywords ***

#KEYWORDS PREENCHIMENTO CORRETO
Acessar Home    
    Wait Until Element Is Visible    ${home.BTN_PAG_INICIAL}    5s
    Click Element                    ${home.BTN_PAG_INICIAL}

Verificar Visibilidade de um componente
    Wait Until Element Is Visible     ${home.COMPONENTE}    5s
    Element Should Be Visible         ${home.COMPONENTE}
