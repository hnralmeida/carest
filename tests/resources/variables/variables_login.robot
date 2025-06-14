*** Settings ***
Resource    ../main.robot

*** Variables ***
${LOGIN_URL}     http://localhost:3000/login
${EMAIL}         fuyu@email.com
${SENHA}         123
${SENHA_ERRADA}         abc

${EMAIL_INPUT}   //input[@type="email"]
${SENHA_INPUT}   //input[@type="password"]
${BTN_LOGIN}     //button[contains(., "Entrar")]
${CLIENTES_SUCESSO}    //h1[contains(., "Clientes")]
${LOGIN_SUCESSO}    //header[contains(@class, "flex shrink-0 items-center gap-2 px-4")]
