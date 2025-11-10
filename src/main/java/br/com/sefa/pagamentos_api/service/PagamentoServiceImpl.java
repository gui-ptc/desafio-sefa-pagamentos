package br.com.sefa.pagamentos_api.service;

import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import br.com.sefa.pagamentos_api.model.request.FiltroRequest;
import br.com.sefa.pagamentos_api.model.request.PagamentoRequest;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumMetodoPagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;
import br.com.sefa.pagamentos_api.persistence.repository.PagamentoRepository;
import br.com.sefa.pagamentos_api.persistence.spec.PagamentoSpecs;


@Service
public class PagamentoServiceImpl implements PagamentoService{

	private final PagamentoRepository pagamentoRepository;
	
	public PagamentoServiceImpl(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }
	
	@Override
	public Pagamento receberPagamento(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}

	@Override
	public Pagamento receberPagamento(PagamentoRequest pagamentoRequest) {
		Pagamento pagamento = new Pagamento();
	    pagamento.setCodDebito(pagamentoRequest.codigoDebito());
	    pagamento.setCpfCnpj(pagamentoRequest.cpfCnpj());

	    var metodo = EnumMetodoPagamento.getEnumMetodoPagamento(pagamentoRequest.metodoPagamento());
	    if (metodo == null) {
	        throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
	            "O método de pagamento informado '" + pagamentoRequest.metodoPagamento() + "' não existe.");
	    }
	    pagamento.setMetodoPagamento(metodo);

	    if (metodo == EnumMetodoPagamento.CARTAO_CREDITO ||
	        metodo == EnumMetodoPagamento.CARTAO_DEBITO) {
	        pagamento.setNumeroCartao(pagamentoRequest.numeroCartao());
	    }

	    pagamento.setValorPagamento(pagamentoRequest.valorPagamento());
	    pagamento.setStatusPagamento(EnumStatusPagamento.PENDENTE_PROCESSAMENTO);

	    return pagamentoRepository.save(pagamento);
	}
	
	@Override
	public Pagamento atualizarStatus(Integer idPagamento, String statusPagamento) {
	    var novoStatus = EnumStatusPagamento.getEnumStatusPagamento(statusPagamento);
	    if (novoStatus == null) {
	        throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
	            "O status informado '" + statusPagamento + "' não existe.");
	    }

	    Pagamento pagamento = pagamentoRepository.findByIdPagamento(idPagamento);
	    if (pagamento == null) {
	        throw new ResponseStatusException(NO_CONTENT,
	            "O Pagamento de ID '" + idPagamento + "' não existe.");
	    }

	    pagamento.setStatusPagamento(novoStatus);
	    return pagamentoRepository.save(pagamento);
	}
	
	@Override
	public Pagamento atualizarPagamento(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}

	@Override
	public List<Pagamento> listarPagamentos(FiltroRequest filtroRequest) {
		return pagamentoRepository.findAll(PagamentoSpecs.comFiltro(filtroRequest));
	}

	@Override
	public Pagamento excluirPagamento(Pagamento pagamento) {
		pagamento.setInativo(true);
		return pagamentoRepository.save(pagamento);
	}
	
	@Override
	public Pagamento inativarPagamento(Integer idPagamento) {
	    Pagamento pagamento = pagamentoRepository.findByIdPagamento(idPagamento);
	    if (pagamento == null) {
	        throw new ResponseStatusException(NO_CONTENT,
	            "O Pagamento de ID '" + idPagamento + "' não existe.");
	    }
	    if (!pagamento.getStatusPagamento().equals(EnumStatusPagamento.PENDENTE_PROCESSAMENTO)) {
	        throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
	            "O Pagamento de ID '" + idPagamento + "' não pode ser inativado porque não se encontra pendente de processamento.");
	    }
	    pagamento.setInativo(true);
	    return pagamentoRepository.save(pagamento);
	}

	@Override
	public Pagamento buscarPagamento(Integer idPagamento) {
		return pagamentoRepository.findByIdPagamento(idPagamento);
	}
}

