package br.com.sefa.pagamentos_api.model.request;

import java.math.BigDecimal;

public record PagamentoRequest (Integer codigoDebito,
								String cpfCnpj,
								String metodoPagamento,
								String numeroCartao,
								BigDecimal valorPagamento) {};				
	


