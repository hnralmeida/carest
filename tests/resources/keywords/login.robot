*** Settings ***
Resource    ../main.robot

*** Keywords ***
Realizar Login
    [Documentation]    Faz login válido no sistema
    Go To    ${LOGIN_URL}
    Wait Until Element Is Visible    ${EMAIL_INPUT}
    Input Text    ${EMAIL_INPUT}    ${EMAIL}
    Input Password    ${SENHA_INPUT}    ${SENHA}
    Click Button    ${BTN_LOGIN}