package br.com.sefa.pagamentos_api.model.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PagamentoRequest (				
				@NotNull(message = "Campo não pode ser nulo")
				Integer codigoDebito,
				@NotBlank(message = "Campo não pode ser nulo ou vazio")
				@Pattern(regexp = "\\d+", message = "Campo aceita somente dígitos")
				String cpfCnpj,
				@NotBlank(message = "Campo não pode ser nulo ou vazio")
				String metodoPagamento,
				String numeroCartao,
				@NotNull(message = "Campo não pode ser nulo")
				BigDecimal valorPagamento) {};
	


