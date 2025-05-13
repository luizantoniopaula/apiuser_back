
package com.senai.user.repository;

import com.senai.user.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    Usuario findByCpf(String cpf);
    
    
}
