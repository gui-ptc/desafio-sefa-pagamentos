package br.com.sefa.pagamentos_api.service;

import java.util.List;

import br.com.sefa.pagamentos_api.model.request.FiltroRequest;
import br.com.sefa.pagamentos_api.model.request.PagamentoRequest;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;

public interface PagamentoService {
	Pagamento buscarPagamento(Integer idPagamento);
	Pagamento receberPagamento(Pagamento pagamento);
	Pagamento receberPagamento(PagamentoRequest pagamentoRequest);
	Pagamento atualizarStatus(Integer idPagamento, String statusPagamento);
	Pagamento atualizarPagamento(Pagamento pagamento);
	List<Pagamento> listarPagamentos(FiltroRequest filtroRequest);
	Pagamento excluirPagamento(Pagamento pagamento);
	Pagamento inativarPagamento(Integer idPagamento);
}

