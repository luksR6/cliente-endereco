package br.com.senac.clientes.controllers;

import br.com.senac.clientes.entitys.Clientes;
import br.com.senac.clientes.entitys.Enderecos;
import br.com.senac.clientes.services.ClientesService;
import br.com.senac.clientes.services.EnderecosService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosService enderecosService;

    @GetMapping("/listar")
    public ResponseEntity<List<Enderecos>> listar() {
        try {
            return ResponseEntity.ok(enderecosService.listarEnderecos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Enderecos>> listarEnderecosPorCliente(@PathVariable Long clienteId) throws Exception {
        List<Enderecos> enderecos = enderecosService.listarEnderecosPorCliente(clienteId);

        return ResponseEntity.ok(enderecos);
    }

    @PostMapping("/criar")
    public ResponseEntity<List<Enderecos>> criarEnderecos(@RequestBody Enderecos endereco) {
        try {
            Enderecos novoEndereco = enderecosService.criarEnderecos(endereco);

            List<Enderecos> enderecos = enderecosService.listarEnderecos();

            return ResponseEntity.ok(enderecos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Enderecos> atualizarEnderecos(@PathVariable Long id ,@RequestBody Enderecos enderecos) {
        try{
            Enderecos enderecosAtualizado = enderecosService.atualizarEnderecos(id, enderecos);
            return ResponseEntity.ok(enderecosAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirEnderecos(@PathVariable Long id) {
        try {
            enderecosService.excluirEnderecos(id);
            return ResponseEntity.ok("Endereço excluído com sucesso!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
