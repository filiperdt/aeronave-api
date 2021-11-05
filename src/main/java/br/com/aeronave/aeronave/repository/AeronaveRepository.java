package br.com.aeronave.aeronave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aeronave.aeronave.model.Aeronave;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
	
	List<Aeronave> findAllByOrderById();
	
	@Query("SELECT COUNT(a) FROM Aeronave a WHERE a.vendido = false")
	Long countVendidoFalse();
	
	@Query("SELECT a.ano/10*10 AS decada, COUNT(a) AS qtde_aeronave FROM Aeronave a GROUP BY decada")
	List<Object[]> countPorDecada();
	
	@Query("SELECT a.marca, COUNT(a) FROM Aeronave a GROUP BY a.marca")
	List<Object[]> countPorMarca();
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM aeronave.aeronave a WHERE a.created BETWEEN CURRENT_DATE -7 AND CURRENT_DATE")
	Long countRegistradaEstaSemana();
	
	List<Aeronave> findByNomeContainingOrderById(String termo);
}
