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


### ENVIRONMENT ###
Variables   ../env_vars.py