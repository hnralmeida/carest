*** Settings ***
Resource    ../main.robot

*** Variables *** 
&{cliente} 
...    PAGE_TITULO=//h1[contains(., "Clientes")]
...    BTN_ADICIONAR=//button[contains(., "Adicionar")]
...    MODAL=//div[contains(@role, "dialog")]
...    INPUT_NOME=//input[@id="name"]
...    INPUT_TELEFONE=//input[@id="telefone"]
...    INPUT_EMAIL=//input[@id="email"]
...    INPUT_CODIGO=//input[@id="codigo"]
...    INPUT_NASCIMENTO=//input[@id="nascimento"]
...    BTN_SALVAR=//button[contains(., "Salvar")]

*** Variables ***
${BROWSER}      chrome
${URL}          http://localhost:3000