package br.com.aeronave.aeronave.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Aeronave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumMarca marca;
	@Column(nullable = false)
	private Integer ano;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private boolean vendido;
	@Column(nullable = false)
	private LocalDateTime created;
	private LocalDateTime updated;

	@Override
	public String toString() {
		return "Aeronave [id=" + id + ", nome=" + nome + ", marca=" + marca + ", ano=" + ano + ", descricao="
				+ descricao + ", vendido=" + vendido + ", created=" 
				+ (created == null? "null" : created.toLocalDate() + " " + created.toLocalTime()) // Ternary operator
				+ ", updated=" 
				+ (updated == null? "null" : updated.toLocalDate() + " " + updated.toLocalTime()) // Ternary operator
				+ "]";
	}
}
