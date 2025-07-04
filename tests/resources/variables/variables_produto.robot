*** Settings ***
Resource    ../main.robot 

*** Variables *** 
&{cliente} 
...    PAGE_TITULO=//h1[contains(., "Produtos")]
...    BTN_ADICIONAR=//button[contains(., "Adicionar")]
...    MODAL=//div[contains(@role, "dialog")]
...    INPUT_NOME=//input[@id="name"]
...    INPUT_VALOR=//input[@id="valor"]
...    INPUT_CUSTO=//input[@id="custo"]
...    INPUT_CODIGO=//input[@id="codigo"]
...    BTN_SALVAR=//button[contains(., "Salvar")]

*** Variables ***
${BROWSER}      chrome
${URL}          http://localhost:3000