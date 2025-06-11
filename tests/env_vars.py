# env_vars.py
import os
from dotenv import load_dotenv

# lê o .env na raiz do projeto
load_dotenv()

# exporta as variáveis como atributos do módulo
URL         = os.getenv("GERAL_URL")
BROWSER     = os.getenv("BROWSER")
# TOKEN_KEY   = os.getenv("TOKEN_KEY")
# FIXED_TOKEN = os.getenv("FIXED_TOKEN")
