// impressora.ts (ou .js se estiver usando JS)
import { writeFileSync } from 'fs';
import { join } from 'path';
import { exec } from 'child_process';

export class Impressora {
  private nomeImpressora = 'Generic / Text Only';

  /**
   * Gera etiquetas ZPL e envia para a impressora
   * @param codigo Código de barras a ser impresso
   * @param quantidade Quantidade de etiquetas
   * @returns Promise com mensagem de sucesso ou erro
   */
  public imprimirEtiqueta(codigo: string, quantidade: number): Promise<string> {
    return new Promise((resolve, reject) => {
      if (!codigo || !quantidade || isNaN(quantidade)) {
        return reject('Código ou quantidade inválida.');
      }

      const etiquetasPorLinha = 3;
      const arrayX = [50, 330, 610];
      const posY = 60;

      let etiquetas = '';
      for (let i = 0; i < quantidade; i++) {
        const posX = arrayX[i % etiquetasPorLinha];

        if (i % etiquetasPorLinha === 0) {
          if (i !== 0) etiquetas += '\n^XZ';
          etiquetas += '\n^XA\n^BY2';
        }

        etiquetas += `\n^FO${posX},${posY}^BCN,100,Y,N,N^FD${codigo}^FS`;
      }

      etiquetas += '\n^XZ';

      const zplPath = join(__dirname, 'etiqueta.zpl');
      writeFileSync(zplPath, etiquetas.trim());

      const comando = `powershell -Command "Get-Content -Path '${zplPath}' | Out-Printer -Name '${this.nomeImpressora}'"`;

      exec(comando, (error, stdout, stderr) => {
        if (error) {
          console.error('Erro ao imprimir:', stderr || error);
          return reject('Falha ao enviar para impressão.');
        }

        resolve('Etiqueta(s) enviada(s) para impressão.');
      });
    });
  }
}

export default Impressora;