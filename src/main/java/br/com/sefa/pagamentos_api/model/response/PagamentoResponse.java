package br.com.sefa.pagamentos_api.model.response;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumMetodoPagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;

public record PagamentoResponse (Integer idPagamento,
						 Integer codDebito,
						 String cpfCnpj,
						 EnumMetodoPagamento metodoPagamento,
						 EnumStatusPagamento statusPagamento,
						 Boolean inativo,
						 String numeroCartao,
						 BigDecimal valorPagamento
) implements Serializable{
	public PagamentoResponse(Pagamento pagamento) {
		this(pagamento.getIdPagamento(),
			 pagamento.getCodDebito(),
			 pagamento.getCpfCnpj(),
			 pagamento.getMetodoPagamento(),
			 pagamento.getStatusPagamento(),
			 pagamento.getInativo(),
			 pagamento.getNumeroCartao(),
			 pagamento.getValorPagamento()		 
			);
	}
}


