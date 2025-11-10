package br.com.sefa.pagamentos_api.persistence.repository;

import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PagamentoRepository extends 
		JpaRepository<Pagamento, Integer>, 
		JpaSpecificationExecutor<Pagamento> {
	Pagamento findByIdPagamento(Integer idPagamento);
}

