package br.com.senac.clientes.services;

import br.com.senac.clientes.entitys.Clientes;
import br.com.senac.clientes.entitys.Enderecos;
import br.com.senac.clientes.repositorys.ClientesRepository;
import br.com.senac.clientes.repositorys.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecosService {

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    public List<Enderecos> listarEnderecos() {
        List<Enderecos> enderecosResult = enderecosRepository.findAll();

        return enderecosResult;
    }

    public List<Enderecos> listarEnderecosPorCliente(Long clienteId) throws Exception{
        // Buscar os endereços com base no ID do cliente
        List<Enderecos> enderecos = enderecosRepository.findByclienteId(clienteId);
        if (enderecos.isEmpty()) {
            throw new Exception("Nenhum endereço encontrado para este cliente.");
        }
        return enderecos;
    }

    public Enderecos criarEnderecos(Enderecos endereco) throws Exception {
        if (endereco.getId() == null) {
            throw new Exception("O ID do cliente é obrigatório");
        }

        long totalEnderecos = enderecosRepository.countByClienteId(endereco.getId());
        if (totalEnderecos >= 3){
            throw new Exception("Cliente atingido o máximo de endereços");
        }


        boolean clienteExiste = clientesRepository.existsById(endereco.getClienteId());
        if (!clienteExiste){
            throw new Exception("Cliente não encontrado");
        }

        return enderecosRepository.save(endereco);
    }

    public Enderecos atualizarEnderecos(Long id, Enderecos endereco) throws Exception {
        Optional<Enderecos> enderecoOptional = enderecosRepository.findById(id);

        if (enderecoOptional.isEmpty()) {
            throw new Exception("Endereço não encontrado");
        }

        Enderecos enderecoExistente = enderecoOptional.get();

        if (!endereco.getClienteId().equals(enderecoExistente.getClienteId())) {
            throw new Exception("Não é possível fazer essa alteração");

        }

        enderecoExistente.setCep(endereco.getCep());
        enderecoExistente.setCidade(endereco.getCidade());
        enderecoExistente.setEstado(endereco.getEstado());
        enderecoExistente.setLogradouro(endereco.getLogradouro());
        enderecoExistente.setNumero(endereco.getNumero());

        return enderecosRepository.save(enderecoExistente);
    }

    public void excluirEnderecos(Long id) throws Exception {
        Optional<Enderecos> enderecoOptional = enderecosRepository.findById(id);

        if (enderecoOptional.isEmpty()) {
            throw new Exception("Endereço não encontrado!");
        }

        enderecosRepository.delete(enderecoOptional.get());
    }
}
