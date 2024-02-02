package com.entra21.gFarm.funcionario;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	 private final FuncionarioService funcionarioService;

	    public FuncionarioController(FuncionarioService funcionarioService) {
	        this.funcionarioService = funcionarioService;
	    }

	    @GetMapping("/{id}")
	    public Optional<FuncionarioDTO> getFuncionarioById(@PathVariable int id) {
	        return funcionarioService.findById(id);
	    }

	    @GetMapping
	    public List<FuncionarioDTO> getAllFuncionarios() {
	        return funcionarioService.findAll();
	    }

	    @PostMapping
	    public void addFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
	        funcionarioService.save(funcionarioDTO);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteFuncionario(@PathVariable int id) {
	        funcionarioService.deleteById(id);
	    }
}
