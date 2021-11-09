package br.com.aeronave.aeronave.service;

import org.springframework.http.ResponseEntity;

import br.com.aeronave.aeronave.dto.request.AeronaveRequestDto;

public interface AeronaveService {
	public ResponseEntity<?> listAll();
	
	public ResponseEntity<?> create(AeronaveRequestDto produtoDto);

	public ResponseEntity<?> read(Long id);

	public ResponseEntity<?> update(Long id, AeronaveRequestDto produto);
	
	public ResponseEntity<?> delete(Long id);
	
	public ResponseEntity<?> listarTodasPorTermo(String termo);
	
	public ResponseEntity<?> contarQtdeNaoVendida();
	
	public ResponseEntity<?> listarQtdePorDecada();
	
	public ResponseEntity<?> listarQtdePorMarca();
	
	public ResponseEntity<?> contarQtdeRegistradasUltimaSemana();
	
	public ResponseEntity<?> listarMarcas();
}