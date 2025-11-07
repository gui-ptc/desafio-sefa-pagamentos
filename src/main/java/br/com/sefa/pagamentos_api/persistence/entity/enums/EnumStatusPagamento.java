package br.com.sefa.pagamentos_api.persistence.entity.enums;

public enum EnumStatusPagamento {
    PENDENTE_PROCESSAMENTO(0), 
    PROCESSADO_SUCESSO(1), 
    PROCESSADO_FALHA(2); 

	private final Integer id;

	public static EnumStatusPagamento getEnumStatusPagamento(String statusPagamento) {
        for (EnumStatusPagamento status : EnumStatusPagamento.values()) {
            if (status.name().equals(statusPagamento)) {
                return status;
            }
        }
        return null;
    }

	EnumStatusPagamento(Integer id) {
		this.id = id;
	}
	
    public Integer getId() {
        return id;
    }
}

