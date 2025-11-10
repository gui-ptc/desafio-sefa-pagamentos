package br.com.sefa.pagamentos_api.persistence.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import br.com.sefa.pagamentos_api.model.request.FiltroRequest;
import br.com.sefa.pagamentos_api.persistence.entity.Pagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;
import jakarta.persistence.criteria.Predicate;

public final class PagamentoSpecs {
    private PagamentoSpecs() {}

    public static Specification<Pagamento> comFiltro(FiltroRequest f) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (f != null) {
                if (f.codigoDebito() != null) {
                    predicates.add(cb.equal(root.get("codDebito"), f.codigoDebito()));
                }
                if (f.cpfCnpj() != null && !f.cpfCnpj().isBlank()) {
                    predicates.add(cb.equal(root.get("cpfCnpj"), f.cpfCnpj()));
                }
                if (f.statusPagamento() != null && !f.statusPagamento().isBlank()) {
                    EnumStatusPagamento st = EnumStatusPagamento.getEnumStatusPagamento(f.statusPagamento());
                    if (st != null) {
                        predicates.add(cb.equal(root.get("statusPagamento"), st));
                    }
                }
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
