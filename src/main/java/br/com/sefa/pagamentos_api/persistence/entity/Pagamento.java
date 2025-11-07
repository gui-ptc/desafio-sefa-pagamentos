package br.com.sefa.pagamentos_api.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumMetodoPagamento;
import br.com.sefa.pagamentos_api.persistence.entity.enums.EnumStatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

public class Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PAGAMENTO")
    @SequenceGenerator(name = "SQ_PAGAMENTO", sequenceName = "SQ_PAGAMENTO", allocationSize = 1)
	private Integer idPagamento;
	
	@NotNull
	@Column(name="cod_debito")
	private Integer codDebito;
	
	@NotNull
	@Column(name="cpf_cnpj")
	private String cpfCnpj;
	
    @NotNull
	@Column(name = "metodo_pagamento")
	private EnumMetodoPagamento metodoPagamento;
    
    @NotNull
	@Column(name = "status_pagamento")
	private EnumStatusPagamento statusPagamento;

	@Column(name = "inativo")
	private Boolean inativo = false;

    public EnumStatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(EnumStatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Boolean getInativo() {
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}

	@Column(name = "numero_cartao")
    private String numeroCartao;
	
    @Column(name = "valor_pagamento")
    private BigDecimal valorPagamento;

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Integer getCodDebito() {
		return codDebito;
	}

	public void setCodDebito(Integer codDebito) {
		this.codDebito = codDebito;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public EnumMetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(EnumMetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}    
}

