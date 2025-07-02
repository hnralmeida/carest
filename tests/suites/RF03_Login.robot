*** Settings ***
Resource    ../resources/main.robot
Test Teardown    Fechar navegador

# RF02.01 
*** Test Cases ***
Realizar Login Com Sucesso
    Open Browser    ${URL}    ${BROWSER}
    Go To    ${LOGIN_URL}
    Wait Until Element Is Visible    ${EMAIL_INPUT}
    Input Text    ${EMAIL_INPUT}    ${EMAIL}
    Input Password    ${SENHA_INPUT}    ${SENHA}
    Click Button    ${BTN_LOGIN}
    Wait Until Element Is Visible    ${LOGIN_SUCESSO}    timeout=5s

Realizar Login Com Falha
    Open Browser    ${URL}    ${BROWSER}
    Go To    ${LOGIN_URL}
    Wait Until Element Is Visible    ${EMAIL_INPUT}
    Input Text    ${EMAIL_INPUT}    ${EMAIL}
    Input Password    ${SENHA_INPUT}    ${SENHA_ERRADA}
    Click Button    ${BTN_LOGIN}
    Wait Until Element Is Visible    ${EMAIL_INPUT}   timeout=5s