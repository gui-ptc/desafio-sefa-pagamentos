package br.com.sefa.pagamentos_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sefa.pagamentos_api.model.request.FiltroRequest;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;
import br.com.sefa.pagamentos_api.persistence.repository.PagamentoRepository;

@Service
public class PagamentoServiceImpl implements PagamentoService{

	private final PagamentoRepository pagamentoRepository = null; 
	
	@Override
	public Pagamento receberPagamento(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}

	@Override
	public Pagamento atualizarPagamento(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}

	@Override
	public List<Pagamento> listarPagamentos(FiltroRequest filtroRequest) {
		if(filtroRequest.codigoDebito() == null &&
				filtroRequest.cpfCnpj() == null &&
				filtroRequest.statusPagamento() == null)
			return pagamentoRepository.findAll();
		
		else if(filtroRequest.codigoDebito() != null &&
				filtroRequest.cpfCnpj() != null &&
				filtroRequest.statusPagamento() != null)
			return pagamentoRepository.findByCodDebitoAndCpfCnpjAndStatuPagamento(
					filtroRequest.codigoDebito(), filtroRequest.cpfCnpj(),
						EnumStatusPagamento.getEnumStatusPagamento(filtroRequest.statusPagamento()));
		
		else if(filtroRequest.codigoDebito() != null &&
				filtroRequest.cpfCnpj() != null &&
				filtroRequest.statusPagamento() == null)
			return pagamentoRepository.findByCodDebitoAndCpfCnpj(
					filtroRequest.codigoDebito(), filtroRequest.cpfCnpj());
		
		else if(filtroRequest.codigoDebito() != null &&
				filtroRequest.cpfCnpj() == null &&
				filtroRequest.statusPagamento() != null)
			return pagamentoRepository.findByCodDebitoAndStatusPagamento(
					filtroRequest.codigoDebito(),
					EnumStatusPagamento.getEnumStatusPagamento(filtroRequest.statusPagamento()));
		
		else if(filtroRequest.codigoDebito() != null &&
				filtroRequest.cpfCnpj() == null &&
				filtroRequest.statusPagamento() == null)
			return pagamentoRepository.findByCodDebito(
					filtroRequest.codigoDebito());
		
		else if(filtroRequest.codigoDebito() == null &&
				filtroRequest.cpfCnpj() != null &&
				filtroRequest.statusPagamento() == null)
			return pagamentoRepository.findByCpfCnpj(
					filtroRequest.cpfCnpj());
		
		else if(filtroRequest.codigoDebito() == null &&
				filtroRequest.cpfCnpj() == null &&
				filtroRequest.statusPagamento() != null)
			return pagamentoRepository.findByStatusPagamento(
					EnumStatusPagamento.getEnumStatusPagamento(filtroRequest.statusPagamento()));
		
		else if(filtroRequest.codigoDebito() == null &&
				filtroRequest.cpfCnpj() != null &&
				filtroRequest.statusPagamento() != null)
			return pagamentoRepository.findByCpfCnpjAndStatusPagamento(filtroRequest.cpfCnpj(),
					EnumStatusPagamento.getEnumStatusPagamento(filtroRequest.statusPagamento()));
		return null;
	}

	@Override
	public Pagamento excluirPagamento(Pagamento pagamento) {
		pagamento.setInativo(true);
		return pagamentoRepository.save(pagamento);
	}

	@Override
	public Pagamento buscarPagamento(Integer idPagamento) {
		return pagamentoRepository.findByIdPagamento(idPagamento);
	}
}

