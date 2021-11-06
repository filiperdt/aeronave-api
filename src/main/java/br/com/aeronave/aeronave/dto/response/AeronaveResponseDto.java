package br.com.aeronave.aeronave.dto.response;

import java.time.LocalDateTime;

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
public class AeronaveResponseDto {
	private Long id;
	private String nome;
	private EnumMarca marca;
	private Integer ano;
	private String descricao;
	private boolean vendido;
	private LocalDateTime created;
	private LocalDateTime updated;
}
