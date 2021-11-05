package br.com.aeronave.aeronave.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.aeronave.aeronave.dto.request.AeronaveRequestDto;
import br.com.aeronave.aeronave.dto.response.AeronaveResponseDto;
import br.com.aeronave.aeronave.model.Aeronave;
import br.com.aeronave.aeronave.repository.AeronaveRepository;
import net.minidev.json.JSONObject;

@Service
public class AeronaveServiceImpl implements AeronaveService {	
	@Autowired
	private AeronaveRepository aeronaveRepository;
	
	public ResponseEntity<JSONObject> retornaJsonMensagem(JSONObject msgResposta, boolean erro, HttpStatus httpStatus) {
		msgResposta.put("erro", erro);
		return new ResponseEntity<JSONObject>(msgResposta, httpStatus);
	}
		
	public List<AeronaveResponseDto> listAll() {
		List<Aeronave> aeronaves = aeronaveRepository.findAll();
		List<AeronaveResponseDto> aeronavesDto = new ArrayList<>();
		
		aeronaves.stream().forEach(aeronave -> {
			AeronaveResponseDto aeronaveDto = mapEntityParaDto(aeronave);
			aeronavesDto.add(aeronaveDto);
		});
		
		return aeronavesDto;
	}

	@Transactional
	public ResponseEntity<?> create(AeronaveRequestDto requisicao) {
		String nome = requisicao.getNome().trim();
		requisicao.setNome(nome);
		
		requisicao.setDescricao(requisicao.getDescricao().trim());
		
		Aeronave aeronave = new Aeronave();
		aeronave = mapDtoParaEntity(requisicao, aeronave); 
		aeronave.setCreated(LocalDateTime.now());
		
		Aeronave aeronaveSalvo = this.aeronaveRepository.save(aeronave);
		AeronaveResponseDto aeronaveResponseDto = mapEntityParaDto(aeronaveSalvo);
		return new ResponseEntity<AeronaveResponseDto>(aeronaveResponseDto, HttpStatus.CREATED);
	}

	public ResponseEntity<?> read(Long id) {
		Optional<Aeronave> optional = this.aeronaveRepository.findById(id);
		
		if(optional.isPresent()) {
			AeronaveResponseDto aeronaveResponseDto = mapEntityParaDto(optional.get());
			return new ResponseEntity<AeronaveResponseDto>(aeronaveResponseDto, HttpStatus.OK);
		} else {
			JSONObject msgResposta = new JSONObject();
			msgResposta.put("message", "Aeronave #"+id+" não encontrado no banco de dados!");
			return retornaJsonMensagem(msgResposta, true, HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	public ResponseEntity<?> update(Long id, AeronaveRequestDto requisicao) {
		Optional<Aeronave> optional = this.aeronaveRepository.findById(id);
		JSONObject msgResposta = new JSONObject(); 
		
		if(optional.isPresent()) {
			String nome = requisicao.getNome().trim();
			requisicao.setNome(nome);
			
			requisicao.setDescricao(requisicao.getDescricao().trim());
			
			Aeronave aeronave = new Aeronave();
			aeronave = mapDtoParaEntity(requisicao, aeronave); 
			
			aeronave.setId(optional.get().getId());
			aeronave.setCreated(optional.get().getCreated());
			aeronave.setUpdated(LocalDateTime.now());
			
			Aeronave aeronaveSalvo = this.aeronaveRepository.save(aeronave);
			AeronaveResponseDto aeronaveResponseDto = mapEntityParaDto(aeronaveSalvo);
			return ResponseEntity.ok().body(aeronaveResponseDto);
		}
		
		msgResposta.put("message", "Aeronave #"+id+" não encontrado no banco de dados!");
		return retornaJsonMensagem(msgResposta, true, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<Aeronave> optional = this.aeronaveRepository.findById(id);
		JSONObject msgResposta = new JSONObject();
		
		if(optional.isPresent()) {
			try {
				this.aeronaveRepository.deleteById(id);
				msgResposta.put("message", "Aeronave #"+id+" excluído com sucesso!");
				return retornaJsonMensagem(msgResposta, false, HttpStatus.OK);
			} catch(Exception e) {
				msgResposta.put("message", "Falha na exclusão! Certifique-se de que o aeronave #"+id+" não esteja relacionado em nenhum pedido!");
				return retornaJsonMensagem(msgResposta, true, HttpStatus.CONFLICT);
			}
		}
		
		msgResposta.put("message", "Aeronave #"+id+" não encontrado no banco de dados!");
		return retornaJsonMensagem(msgResposta, true, HttpStatus.NOT_FOUND);
	}
	
	public AeronaveResponseDto mapEntityParaDto(Aeronave aeronave) {
		AeronaveResponseDto aeronaveDto = AeronaveResponseDto.builder()
		.id(aeronave.getId())
		.nome(aeronave.getNome())
		.marca(aeronave.getMarca())
		.ano(aeronave.getAno())
		.descricao(aeronave.getDescricao())
		.vendido(aeronave.isVendido())
		.created(aeronave.getCreated())
		.updated(aeronave.getUpdated())
		.build();
		
		return aeronaveDto;
	}
	
	public Aeronave mapDtoParaEntity(AeronaveRequestDto aeronaveDto, Aeronave aeronave) {
		aeronave = Aeronave.builder()
		.nome(aeronaveDto.getNome())
		.marca(aeronaveDto.getMarca())
		.ano(aeronaveDto.getAno())
		.descricao(aeronaveDto.getDescricao())
		.vendido(aeronaveDto.isVendido())
		.build();
		
		return aeronave;
	}

	@Override
	public ResponseEntity<?> listarPorParametro(String termo) {
		
		return null;
	}
}