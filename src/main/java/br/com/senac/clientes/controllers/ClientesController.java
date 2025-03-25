package br.com.senac.clientes.controllers;

import br.com.senac.clientes.entitys.Clientes;
import br.com.senac.clientes.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/carregar")
    public ResponseEntity<List<Clientes>> carregarClientes() {
        try {
            return ResponseEntity.ok(clientesService.carregarClientes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> cadastrarCliente(Clientes cliente) {
        try {
            return ResponseEntity.ok(clientesService.criarCliente(cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizarCliente(Long id , Clientes cliente) {
        try {
            return ResponseEntity.ok(clientesService.atualizarCliente(id, cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCliente(Long id) {
        try {
            clientesService.excluirCliente(id);

            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
