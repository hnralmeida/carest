import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}


export function dateToISO(date: Date) {
  if (isNaN(date.getTime())) {
    return ""
  }
  return date.toISOString().split("T")[0]
}

export function dateToReadable(date: Date) {
  return date.toLocaleDateString()
}

export function ISODateToDate(d: Date): string {
  const dia = String(d.getDate()).padStart(2, '0');     // Dia do mês
  const mes = String(d.getMonth() + 1).padStart(2, '0'); // Mês (corrigido +1)
  const ano = d.getFullYear();

  return `${ano}-${mes}-${dia}`;
}

export const formatarParaMoeda = (valor: string, formatado?: boolean) => {
  // Remove qualquer caractere que não seja número
  const numero = valor.replace(/[^0-9.]/g, "");

  // Converte para número com 2 casas decimais
  const valorNumerico = formatado ? (Number(numero)).toFixed(2) : (Number(numero) / 100).toFixed(2);

  // Formata em BRL
  return new Intl.NumberFormat("pt-BR", {
    style: "currency",
    currency: "BRL"
  }).format(Number(valorNumerico));
};

export const moedaParaNumero = (valor: string): number => {
  if (!valor) return 0;
  console.log("valor", valor);

  const apenasNumeros = valor
    .replace(/\s/g, '')         // remove espaços
    .replace('R$', '')          // remove símbolo do real
    .replace(',', '.');         // troca vírgula decimal por ponto

  return parseFloat(apenasNumeros) || 0;
};

export const formatTelefone = (value: string) => {
  // Remove tudo que não for número
  const cleaned = value.replace(/\D/g, '');

  // Aplica a máscara com base no tamanho
  if (cleaned.length <= 10) {
    // Ex: (11) 2345-6789
    return cleaned.replace(/(\d{0,2})(\d{0,4})(\d{0,4})/, function (_, ddd, parte1, parte2) {
      let result = '';
      if (ddd) result += `(${ddd}`;
      if (ddd && ddd.length === 2) result += `) `;
      if (parte1) result += parte1;
      if (parte2) result += `-${parte2}`;
      return result;
    });
  } else {
    // Ex: (11) 91234-5678
    return cleaned.replace(/(\d{0,2})(\d{0,5})(\d{0,4})/, function (_, ddd, parte1, parte2) {
      let result = '';
      if (ddd) result += `(${ddd}`;
      if (ddd.length === 2) result += `) `;
      if (parte1) result += parte1;
      if (parte2) result += `-${parte2}`;
      return result;
    });
  }
};
