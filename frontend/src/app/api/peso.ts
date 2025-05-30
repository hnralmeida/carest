import { Balanca } from '@/lib/balanca'; // sua classe
import type { NextApiRequest, NextApiResponse } from 'next';

const balanca = new Balanca();

async function handler(req: NextApiRequest, res: NextApiResponse) {
  try {
    if (!balanca['serialPort']) await balanca.inicializarPorta();

    const resultado = await balanca.obterPeso();
    res.status(200).json(resultado);
  } catch (err: any) {
    console.error('Erro ao obter peso:', err.message);
    res.status(500).json({ erro: err.message });
  }
}

export { handler as GET, handler as POST}