package br.com.aeronave.aeronave.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.aeronave.aeronave.model.EnumMarca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AeronaveRequestDto {
	@NotNull(message = "Nome não pode ser null")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	@NotNull(message = "Marca não pode ser null")
	private EnumMarca marca;
	@NotNull(message = "Ano não pode ser null")
	@PositiveOrZero(message = "Ano não pode ser negativo")
	private Integer ano;
	@NotNull(message = "Descrição não pode ser null")
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	@NotNull(message = "Vendido não pode ser null")
	private boolean vendido;
}
