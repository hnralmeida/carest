*** Settings ***
Resource    ../main.robot


*** Keywords ***
Dado que eu acesse o Carest
    # 1) Abre o browser direto no domínio alvo
    Open Browser    ${URL}    ${BROWSER}
    # 2) Navega para a página principal que você vai testar
    Realizar Login


Fechar navegador
    Close Browser