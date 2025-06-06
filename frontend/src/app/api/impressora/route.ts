import Impressora from '@/lib/impressora';
import { NextRequest } from 'next/server';

const impressora = new Impressora();

export async function POST(req: NextRequest) {
  try {
    const body = await req.json();
    const code = body.code;

    if (!code || typeof code !== 'string') {
      return new Response(JSON.stringify({ erro: 'Código é obrigatório e deve ser uma string.' }), {
        status: 400,
      });
    }

    const msg = await impressora.imprimirEtiqueta(code, 1);
    console.log(msg);

    return new Response(JSON.stringify({ mensagem: msg }), {
      status: 200,
    });
  } catch (err: any) {
    console.error('Erro ao imprimir:', err.message);
    return new Response(JSON.stringify({ erro: err.message }), {
      status: 500,
    });
  }
}
export async function GET(req: NextRequest) {
  const { searchParams } = new URL(req.url);
  const code = searchParams.get('code');

  if (!code || typeof code !== 'string') {
    return new Response(JSON.stringify({ erro: 'Código é obrigatório e deve ser uma string.' }), {
      status: 400,
    });
  }

  try {
    const msg = await impressora.imprimirEtiqueta(code, 1);
    return new Response(JSON.stringify({ mensagem: msg }), {
      status: 200,
    });
  } catch (err: any) {
    console.error('Erro ao imprimir:', err.message);
    return new Response(JSON.stringify({ erro: err.message }), {
      status: 500,
    });
  }
}
