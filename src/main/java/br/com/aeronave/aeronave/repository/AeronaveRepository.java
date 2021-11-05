package br.com.aeronave.aeronave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aeronave.aeronave.model.Aeronave;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
	@Query("SELECT a FROM Aeronave a WHERE unaccent(UPPER(a.nome)) LIKE unaccent(UPPER(?1))")
	List<Aeronave> listarTodasPorTermo(String termo);
	
	@Query("SELECT COUNT(a) FROM Aeronave a WHERE a.vendido = false")
	Long contarQtdeNaoVendida();
	
	@Query("SELECT a.ano/10*10 AS decada, COUNT(a) AS qtde_aeronave FROM Aeronave a GROUP BY decada")
	List<Object[]> listarQtdePorDecada();
	
	@Query("SELECT a.marca, COUNT(a) FROM Aeronave a GROUP BY a.marca")
	List<Object[]> listarQtdePorMarca();
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM aeronave.aeronave a WHERE a.created BETWEEN now()\\:\\:timestamp - (interval '7d') AND now()\\:\\:timestamp")
	List<Aeronave> listarRegistradasUltimaSemana();
}
