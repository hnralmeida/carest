import Impressora from '@/lib/impressora';
import type { NextApiRequest, NextApiResponse } from 'next';

const impressora = new Impressora();

async function handler(req: NextApiRequest, res: NextApiResponse) {
  try {
    const code = req.method === 'POST' ? req.body.code : req.query.code;

    if (!code || typeof code !== 'string') {
      return res.status(400).json({ erro: 'Código é obrigatório e deve ser uma string.' });
    }

    const msg = await impressora.imprimirEtiqueta(code, 1);
    console.log(msg);
    res.status(200).json({ mensagem: msg });
  } catch (err: any) {
    console.error('Erro ao imprimir:', err.message);
    res.status(500).json({ erro: err.message });
  }
}

export { handler as GET, handler as POST };
