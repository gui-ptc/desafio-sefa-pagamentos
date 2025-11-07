package br.com.sefa.pagamentos_api.persistence.entity.enums;

public enum EnumMetodoPagamento {
    BOLETO(0), 
    PIX(1), 
    CARTAO_CREDITO(2),
    CARTAO_DEBITO(3); 

	private final Integer id;

	public static EnumMetodoPagamento getEnumMetodoPagamento(String metodoPagamento) {
        for (EnumMetodoPagamento metodo : EnumMetodoPagamento.values()) {
            if (metodo.name().equals(metodoPagamento)) {
                return metodo;
            }
        }
        return null;
    }

	EnumMetodoPagamento(Integer id) {
		this.id = id;
	}
	
    public Integer getId() {
        return id;
    }
}

