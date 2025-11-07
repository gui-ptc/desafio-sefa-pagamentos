package br.com.sefa.pagamentos_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController {
	
	private final PagamentoService pagamento Service;
	
	public PagamentosController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	
	@PostMapping
    public ResponseEntity<PagamentoResponse> receberPagamento(
            @RequestBody PagamentoRequest pagamentoRequest) {
        Pagamento pagamento = pagamentoService.receberPagamento(pagamentoRequest);
        return ResponseEntity.created(new PagamentoResponse(pagamento));
    }

	@PutMapping("/{idPagamento}/{statusPagamento}")
    public ResponseEntity<PagamentoResponse> atualizarPagamento(
            @PathVariable int idPagamento,
            @PathVariable String statusPagamento) {
        if(EnumStatusPagamento.getDescricao(statusPagamento) == null)
        	return ResponseEntity.unprocessableEntity().
		Pagamento pagamento = pagamentoService.atualizarPagamento(idPagamento, statusPagamento);
        return ResponseEntity.ok(new PagamentoResponse(pagamento));
    }
	
	@GetMapping
	public ResponseEntity<List<PagamentoResponse>> listarPagamentos(
			@RequestBody FiltroRequest filtroRequest) {
		List<Pagamento> pagamentos = pagamentoService.listarPagamentos(filtroRequest);
	    if (pagamentos == null) { 
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(new ArrayList<PagamentoResponse>(pagamentos));
	}

	@DeleteMapping("/{idPagamento}")
	public ResponseEntity<Void> excluirPagamento(
			@PathVariable int idPagamento) {
		pagamentoService.excluirPagamento(idPagamento);
		return ResponseEntity.noContent().build();
	}
}


