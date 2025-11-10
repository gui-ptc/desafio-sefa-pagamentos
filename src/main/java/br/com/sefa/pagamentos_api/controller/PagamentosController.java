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
import br.com.sefa.pagamentos_api.model.request.FiltroRequest;
import br.com.sefa.pagamentos_api.model.request.PagamentoRequest;
import br.com.sefa.pagamentos_api.model.response.PagamentoResponse;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.service.PagamentoService;
import jakarta.validation.Valid;


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
		Pagamento pagamento = pagamentoService.receberPagamento(pagamentoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PagamentoResponse(pagamento));
    }

	@PutMapping("/{idPagamento}/{statusPagamento}")
    public ResponseEntity<PagamentoResponse> atualizarPagamento(
            @PathVariable Integer idPagamento,
            @PathVariable String statusPagamento) {
		    Pagamento pagamento = pagamentoService.atualizarStatus(idPagamento, statusPagamento);
		    return ResponseEntity.ok(new PagamentoResponse(pagamento));
    }
	
	@GetMapping
	public ResponseEntity<List<PagamentoResponse>> listarPagamentos(
			@RequestBody @Valid FiltroRequest filtroRequest) {
		
		List<Pagamento> pagamentos = pagamentoService.listarPagamentos(filtroRequest);
		if (pagamentos.isEmpty()) { 
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

		Pagamento pagamento = pagamentoService.inativarPagamento(idPagamento);
		return ResponseEntity.status(HttpStatus.OK).body(new PagamentoResponse(pagamento));
	}
}


