import { Component } from '@angular/core';
import { CreditoService } from '../services/credito.service';
import { Credito } from '../models/credito.model';

@Component({
  selector: 'app-consulta-credito',
  templateUrl: './credito-consulta.component.html'
})
export class ConsultaCreditoComponent {
  numeroNfse: string = '';
  numeroCredito: string = '';
  creditos: Credito[] = [];
  mensagem: string = '';

  constructor(private creditoService: CreditoService) {}

  consultar() {
    this.mensagem = ''; // Limpa a mensagem anterior
    this.creditos = []; // Limpa os créditos anteriores
    if (this.numeroNfse) {
      console.log('Iniciando consulta para NFS-e:', this.numeroNfse);
      this.creditoService.findByNumeroNfse(this.numeroNfse).subscribe({
        next: (data) => {
          console.log('Dados recebidos:', data);
          this.creditos = data || []; // Garante que creditos seja um array
          this.mensagem = data.length > 0 ? '' : 'Nenhum crédito encontrado para a NFS-e informada.';
        },
        error: (error) => {
          console.error('Erro na consulta:', error);
          this.creditos = [];
          this.mensagem = 'Erro ao consultar créditos. Tente novamente.';
        }
      });
    } else if (this.numeroCredito) {
      console.log('Iniciando consulta para Crédito:', this.numeroCredito);
      this.creditoService.findByNumeroCredito(this.numeroCredito).subscribe({
        next: (data) => {
          console.log('Dados recebidos:', data);
          this.creditos = data ? [data] : [];
          this.mensagem = data ? '' : 'Nenhum crédito encontrado para o número informado.';
        },
        error: (error) => {
          console.error('Erro na consulta:', error);
          this.creditos = [];
          this.mensagem = 'Erro ao consultar créditos. Tente novamente.';
        }
      });
    } else {
      this.mensagem = 'Por favor, informe o número da NFS-e ou do crédito.';
    }
  }
}