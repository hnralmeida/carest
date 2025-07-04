*** Settings ***   
Library    SeleniumLibrary
Library    FakerLibrary    #Locale=pt_BR


### Data ###
Resource    data/dados_registro.robot


### Shared ###
Resource    shared/setup_teardown.robot


### Pages ###
Resource    keywords/home.robot
Resource    variables/variables_home.robot

Resource    keywords/produto.robot
Resource    variables/variables_produto.robot

Resource    keywords/login.robot
Resource    variables/variables_login.robot

Resource    keywords/vendas.robot
Resource    variables/variables_vendas.robot


### ENVIRONMENT ###
Variables   ../env_vars.py