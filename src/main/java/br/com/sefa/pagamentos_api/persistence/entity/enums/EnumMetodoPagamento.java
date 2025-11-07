package br.com.sefa.pagamentos_api.persistence.entity.enums;

public enum EnumMetodoPagamento {
    BOLETO(0, "Boleto"), 
    PIX(1, "Pix"), 
    CARTAO_CREDITO(2, "Cartão de Crédito"),
    CARTAO_DEBITO(3, "Cartão de Débito"); 

	private final Integer id;
    private final String descricao;

	public static EnumMetodoPagamento getEnumMetodoPagamento(Integer id) {
        for (EnumMetodoPagamento metodoPagamento : EnumMetodoPagamento.values()) {
            if (metodoPagamento.getId().equals(id)) {
                return metodoPagamento;
            }
        }
        return null;
    }

	EnumMetodoPagamento(Integer id, String descricao) {
		this.id = id;
	    this.descricao = descricao;
	}
	
    public String getDescricao() {
        return this.descricao;
    }

    public Integer getId() {
        return id;
    }
}

