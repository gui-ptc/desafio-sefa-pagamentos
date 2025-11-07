package br.com.sefa.pagamentos_api.service;

import java.util.List;

import br.com.sefa.pagamentos_api.model.request.PagamentoRequest;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;

public interface PagamentoService {
	Pagamento receberPagamento(PagamentoRequest pagamentoRequest);
	Pagamento atualizarPagamento(int idPagamento, String statusPagamento);
	List<Pagamento> listarPagamentos(FiltroRequest filtroRequest);
	void excluirPagamento(int idPagamento);
}

