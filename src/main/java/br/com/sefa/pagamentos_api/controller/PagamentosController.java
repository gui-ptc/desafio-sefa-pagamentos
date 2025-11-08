package br.com.sefa.pagamentos_api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sefa.pagamentos_api.model.request.FiltroRequest;
import br.com.sefa.pagamentos_api.model.request.PagamentoRequest;
import br.com.sefa.pagamentos_api.model.response.PagamentoResponse;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumMetodoPagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;
import br.com.sefa.pagamentos_api.service.PagamentoService;
import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController {
	
	private final PagamentoService pagamentoService;
	
	public PagamentosController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	
	@PostMapping
    public ResponseEntity<PagamentoResponse> receberPagamento(
            @RequestBody @Valid PagamentoRequest pagamentoRequest) {
		Pagamento pagamento = new Pagamento();
		pagamento.setCodDebito(pagamentoRequest.codigoDebito());
		pagamento.setCpfCnpj(pagamentoRequest.cpfCnpj());
		var metodo = EnumMetodoPagamento.getEnumMetodoPagamento(pagamentoRequest.metodoPagamento());
		if (metodo == null) {
			throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
		    	"O método de pagamento informado '" + pagamentoRequest.metodoPagamento() + "' não existe.");
		}		
		
		pagamento.setMetodoPagamento(EnumMetodoPagamento.getEnumMetodoPagamento(pagamentoRequest.metodoPagamento()));
		if(pagamento.getMetodoPagamento().equals(EnumMetodoPagamento.CARTAO_CREDITO) ||
			pagamento.getMetodoPagamento().equals(EnumMetodoPagamento.CARTAO_DEBITO)) {
			pagamento.setNumeroCartao(pagamentoRequest.numeroCartao());
		}
		
		pagamento.setValorPagamento(pagamentoRequest.valorPagamento());
		pagamento.setStatusPagamento(EnumStatusPagamento.PENDENTE_PROCESSAMENTO);

		pagamento = pagamentoService.receberPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PagamentoResponse(pagamento));
    }

	@PutMapping("/{idPagamento}/{statusPagamento}")
    public ResponseEntity<PagamentoResponse> atualizarPagamento(
            @PathVariable Integer idPagamento,
            @PathVariable String statusPagamento) {
		var novoStatus = EnumStatusPagamento.getEnumStatusPagamento(statusPagamento);
		 if (novoStatus == null) {
		        throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
		            "O status informado '" + statusPagamento + "' não existe.");
		    }
		Pagamento pagamento = pagamentoService.buscarPagamento(idPagamento);
		if(pagamento == null)
			throw new ResponseStatusException(NO_CONTENT, 
					"O Pagamento de ID '".concat(String.valueOf(idPagamento)).concat("' não existe."));
		 	pagamento.setStatusPagamento(novoStatus); 
		    pagamento = pagamentoService.atualizarPagamento(pagamento);
		    return ResponseEntity.ok(new PagamentoResponse(pagamento));
    }
	
	@GetMapping
	public ResponseEntity<List<PagamentoResponse>> listarPagamentos(
			@RequestBody @Valid FiltroRequest filtroRequest) {
		List<Pagamento> pagamentos = pagamentoService.listarPagamentos(filtroRequest);
	    if (pagamentos == null) { 
	    	return ResponseEntity.noContent().build();
	    }
	    List<PagamentoResponse> pagamentosResponse = new ArrayList<>();
	    for(Pagamento pag : pagamentos) {
	    	pagamentosResponse.add(new PagamentoResponse(pag));
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(pagamentosResponse);
	}

	@DeleteMapping("/{idPagamento}")
	public ResponseEntity<PagamentoResponse> excluirPagamento(
			@PathVariable Integer idPagamento) {
		Pagamento pagamento = pagamentoService.buscarPagamento(idPagamento);
		if(pagamento == null)
			throw new ResponseStatusException(NO_CONTENT, 
					"O Pagamento de ID '".concat(String.valueOf(idPagamento)).concat("' não existe."));
		if(!pagamento.getStatusPagamento().equals(EnumStatusPagamento.PENDENTE_PROCESSAMENTO)) {
			throw new ResponseStatusException(UNPROCESSABLE_ENTITY, 
					"O Pagamento de ID '".concat(String.valueOf(idPagamento)).
						concat("' não pode ser inativado porque não se encontra pendente de processamento."));
		}
		pagamento = pagamentoService.excluirPagamento(pagamento);
		return ResponseEntity.status(HttpStatus.OK).body(new PagamentoResponse(pagamento));
	}
}


