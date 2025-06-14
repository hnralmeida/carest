*** Settings ***
Resource    ../main.robot

*** Variables *** 
&{home} 
...    COMPONENTE=//p[@class="text-gray-600"]        #css:div.border\.rounded-lg\.p-3\.border-gray-300.cursor-pointer            
...    BTN_PAG_INICIAL=//a[@class="bg-white/20 font-semibold router-link-exact-active block px-3 py-2 rounded hover:bg-white/10 transition-colors"]

*** Variables ***
${DB_HOST}      127.0.0.1
${DB_PORT}      5432
${ENV_NAME}     local
${BROWSER}      chrome
${URL}          http://localhost:3000
# É possível fazer uma lista com as opções de uma combobox da seguinte maneira:
# @{ComboBox}
# ...       //option[contains(.,'opcao1')]
# ...       //option[contains(.,'opcao2')]
# ...       //option[contains(.,'opcao3')]
# ...       //option[contains(.,'opcao4')]
# ...       //option[contains(.,'opcao5')]
# ...       //option[contains(.,'opcao6')]
# ...       //option[contains(.,'opcao7')]
# 
# Também é possível adicionar essa lista num dicionário de dados, para chamar ela pela página específica 