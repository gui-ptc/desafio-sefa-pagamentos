package br.com.sefa.pagamentos_api.service;

import br.com.sefa.pagamentos_api.model.request.PagamentoRequest;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;

public class PagamentoServiceImpl implements PagamentoService{

	private final PagamentoRepository pagamentoRepository; 
	
	@Override
	public Pagamento receberPagamento(PagamentoRequest pagamentoRequest) {
		Pagamento pagamento = new Pagamento();
		pagamento.set
		return pagamentoRepository.receberPagamento()
	}

	@Override
	public Pagamento atualizarPagamento(int idPagamento, String statusPagamento) {
		returnLocale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		//
		
		sc.close();
		
	}

	@Override
	public List<Pagamento> listarPagamentos(FiltroRequest filtroRequest) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		//
		
		sc.close();
		
	}

	@Override
	public void excluirPagamento(int idPagamento) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		//
		
		sc.close();
		
	}

}

