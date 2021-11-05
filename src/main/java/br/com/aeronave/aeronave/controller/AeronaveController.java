package br.com.aeronave.aeronave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.aeronave.aeronave.dto.request.AeronaveRequestDto;
import br.com.aeronave.aeronave.dto.response.AeronaveResponseDto;
import br.com.aeronave.aeronave.service.AeronaveService;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/aeronaves")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AeronaveController {
	@Autowired
	private AeronaveService aeronaveService;
	
	public JSONObject analisaBindingResult(BindingResult bindingResult, JSONObject jsonMessages){
		List<FieldError> errors = bindingResult.getFieldErrors();
		for(FieldError erro : errors) {
			jsonMessages.put(erro.getField(), erro.getDefaultMessage());
		}
		
		return jsonMessages;
	}
	
	public ResponseEntity<JSONObject> geraErroDoBindingResult(BindingResult bindingResult) {
		JSONObject jsonMessages = new JSONObject();
		jsonMessages = analisaBindingResult(bindingResult, jsonMessages);
		jsonMessages.put("erro", true);
		
		return new ResponseEntity<JSONObject>(jsonMessages, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<?> listAll() {
		List<AeronaveResponseDto> aeronavesDto = this.aeronaveService.listAll();
		return new ResponseEntity<>(aeronavesDto, HttpStatus.OK);
	}
	
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody AeronaveRequestDto requisicao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ResponseEntity<JSONObject> responseEntity = geraErroDoBindingResult(bindingResult);
			return responseEntity;
		}else {
			ResponseEntity<?> responseEntity = aeronaveService.create(requisicao);
			return responseEntity;
		}
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> read(@PathVariable Long id) {
		ResponseEntity<?> responseEntity = aeronaveService.read(id);
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AeronaveRequestDto requisicao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ResponseEntity<JSONObject> responseEntity = geraErroDoBindingResult(bindingResult);
			return responseEntity;
		}else {
			ResponseEntity<?> responseEntity = aeronaveService.update(id, requisicao);
			return responseEntity;
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable Long id) {
		ResponseEntity<?> responseEntity = aeronaveService.delete(id);
		return responseEntity;
	}
	
	@GetMapping("/find/{termo}")
	@ResponseBody
	public ResponseEntity<?> listarPorParametro(@PathVariable String termo) {
		ResponseEntity<?> responseEntity = aeronaveService.listarPorParametro(termo);
		return responseEntity;
	}
}