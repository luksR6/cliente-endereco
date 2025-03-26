package br.com.senac.clientes.services;

import br.com.senac.clientes.entitys.Clientes;
import br.com.senac.clientes.repositorys.ClientesRepository;
import br.com.senac.clientes.utils.ValidacoesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.senac.clientes.utils.ValidacoesUtils.validarSeRegistroExiste;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes criarCliente(Clientes cliente) {
        // valida se o email não está nulo
        if(Objects.isNull(cliente.getEmail())) {
            throw new RuntimeException("Email não informado!");
        }

        // varifica se já existe algum cliente com o email já cadastrado na base
        /*Optional<Clientes> clienteResult = clientesRepository.buscarClienteByEmail(cliente.getEmail());
        if(clienteResult.isPresent()) {
            throw new RuntimeException("Já exite cliente cadastrado no banco com o email informado!");
        }*/

        // zera id do cliente para garantir que o banco gere o ID
        cliente.setId(null);

        return clientesRepository.save(cliente);
    }

    public Clientes atualizarCliente(Long id, Clientes cliente) {
        ValidacoesUtils.validarSeRegistroExiste(clientesRepository, id);

        // valida se o email não está nulo
        if(Objects.isNull(cliente.getEmail())) {
            throw new RuntimeException("Email não informado!");
        }

        // adicionado id passado por parametro no objeto
        cliente.setId(id);

        return clientesRepository.save(cliente);
    }

    public List<Clientes> carregarClientes() {
        List<Clientes> clientesResult = clientesRepository.findAll();

        return clientesResult;
    }

    public void excluirCliente(Long id) {
        validarSeRegistroExiste(clientesRepository, id);

        clientesRepository.deleteById(id);
    }
}
