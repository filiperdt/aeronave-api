package br.com.aeronave.aeronave.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.aeronave.aeronave.dto.request.AeronaveRequestDto;
import br.com.aeronave.aeronave.dto.response.AeronaveResponseDto;

public interface AeronaveService {
	public List<AeronaveResponseDto> listAll();
	
	public ResponseEntity<?> create(AeronaveRequestDto produtoDto);

	public ResponseEntity<?> read(Long id);

	public ResponseEntity<?> update(Long id, AeronaveRequestDto produto);
	
	public ResponseEntity<?> delete(Long id);
	
	public ResponseEntity<?> listarPorParametro(String termo);
}