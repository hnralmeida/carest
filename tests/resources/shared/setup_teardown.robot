*** Settings ***
Resource    ../main.robot


*** Keywords ***
Dado que eu acesse o Conecta
    # 1) Abre o browser direto no domínio alvo
    Open Browser    ${URL}    ${BROWSER}
    # 2) Injeta o token no localStorage da origem
    Execute Javascript    window.localStorage.setItem('${TOKEN_KEY}', '${FIXED_TOKEN}');
    # 3) Navega para a página principal que você vai testar
    Go To    ${URL}


Fechar navegador
    Close Browser