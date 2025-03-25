package br.com.senac.clientes.repositorys;

import br.com.senac.clientes.entitys.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {
    List<Enderecos> listarEnderecosPorCliente(Long clienteId);

    @Query("SELECT COUNT(e) FROM Enderecos e WHERE e.id = :clienteId")
    long countByClienteId(@Param("clienteId") Long clienteId);
}
