import { SerialPort } from "serialport";

export class Balanca {
  private static readonly SERIAL_PORT = 'COM3'; // Use static para constantes da classe
  private static readonly BAUD_RATE = 4800;

  private serialPort: SerialPort | undefined;
  private lastWeight: number | null = null;
  private lastWeightTimestamp: string | null = null;
  private isProcessingRequest = false;

  // Inicializa a conexão com a balança
  public inicializarPorta(): Promise<void> {
    return new Promise((resolve, reject) => {
      this.serialPort = new SerialPort({
        path: Balanca.SERIAL_PORT,
        baudRate: Balanca.BAUD_RATE,
        dataBits: 8,
        parity: 'none',
        stopBits: 1,
        autoOpen: false,
      });

      this.serialPort.open((err) => {
        if (err) {
          console.error('Erro ao abrir a porta serial:', err.message);
          return reject(err);
        }
        console.log(`Conectado à porta serial ${Balanca.SERIAL_PORT} a ${Balanca.BAUD_RATE} baud`);

        this.serialPort!.on('data', (data) => this.onDataReceived(data));
        this.serialPort!.on('error', (err) =>
          console.error('Erro na porta serial:', err.message)
        );

        resolve();
      });
    });
  }

  // Processa os dados recebidos da balança
  private onDataReceived(data: Buffer) {
    if (!this.isProcessingRequest) return;

    try {
      const asciiStr = data.toString();
      console.log(`Dados recebidos: ${asciiStr}`);

      const weightMatch = asciiStr.match(/(\d+[\.,]\d+)|(\d+)/);
      if (weightMatch) {
        const extractedWeight = parseFloat(weightMatch[0].replace(',', '.'));
        if (!isNaN(extractedWeight)) {
          this.lastWeight = extractedWeight;
          this.lastWeightTimestamp = new Date().toISOString();
          console.log(`Peso obtido: ${this.lastWeight}`);
          this.isProcessingRequest = false;
        }
      }
    } catch (error: any) {
      console.error('Erro ao processar dados:', error.message);
      this.isProcessingRequest = false;
    }
  }

  // Envia o comando "T" e aguarda o peso
  public obterPeso(timeout = 3000): Promise<{ peso: number, timestamp: string | null }> {
    if (!this.serialPort || !this.serialPort.isOpen) {
      return Promise.reject(new Error('Porta serial não está aberta'));
    }

    return new Promise((resolve, reject) => {
      this.isProcessingRequest = true;
      this.lastWeight = null;

      const command = Buffer.from('T');
      this.serialPort!.write(command, (err) => {
        if (err) {
          this.isProcessingRequest = false;
          return reject(err);
        }
        console.log('Comando "T" enviado');

        const start = Date.now();

        const interval = setInterval(() => {
          if (!this.isProcessingRequest || Date.now() - start >= timeout) {
            clearInterval(interval);

            if (this.lastWeight !== null) {
              resolve({ peso: this.lastWeight, timestamp: this.lastWeightTimestamp });
            } else {
              reject(new Error('Não foi possível obter o peso dentro do tempo limite'));
            }
          }
        }, 100);
      });
    });
  }
}
