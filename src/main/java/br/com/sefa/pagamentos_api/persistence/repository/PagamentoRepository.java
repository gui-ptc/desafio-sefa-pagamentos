package br.com.sefa.pagamentos_api.persistence.repository;

import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
	Pagamento findByIdPagamento(Integer idPagamento);
	List<Pagamento> findByCodDebitoAndCpfCnpjAndStatusPagamento(Integer codigoDebito, String cpfCnpj, EnumStatusPagamento statusPagamento);
	List<Pagamento> findByCodDebitoAndCpfCnpj(Integer codigoDebito, String cpfCnpj);
	List<Pagamento> findByCodDebitoAndStatusPagamento(Integer codigoDebito, EnumStatusPagamento statuPagamento);
	List<Pagamento> findByCodDebito(Integer codigoDebito);
	List<Pagamento> findByCpfCnpjAndStatusPagamento(String cpfCnpj, EnumStatusPagamento statusPagamento);
	List<Pagamento> findByCpfCnpj(String cpfCnpj);
	List<Pagamento> findByStatusPagamento(EnumStatusPagamento statusPagamento);
}

