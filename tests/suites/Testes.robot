*** Settings ***
Suite Setup    Log    Iniciando execução repetitiva
Suite Teardown    Log    Finalizando execução repetitiva
Library    SeleniumLibrary

*** Test Cases ***
Executar Login Com Sucesso 10 Vezes
    FOR    ${i}    IN RANGE    10
        Go to    RF03_Login.robot
    END
