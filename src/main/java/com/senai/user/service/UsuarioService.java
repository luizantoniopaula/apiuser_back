
package com.senai.user.service;

import com.senai.user.entity.Usuario;
import com.senai.user.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public Long inserirNovoUsuario(Usuario user){
        
        if(user.getNome() == null || user.getEmail() == null){
            return null;
        } else {
            return usuarioRepository.save(user).getId();
        }
    }
    
    public Boolean excluirUsuario(Long id){
        
        if(usuarioRepository.getReferenceById(id).getId() == null){
            return null;
        } else {
            usuarioRepository.deleteById(id);
            return true;
        }
    }
        
    public List<Usuario> listarUsuarios(){
        
        return usuarioRepository.findAll();
    }
        
    public Boolean alterarUsuario(Usuario user){
        
        Usuario userBd = usuarioRepository.getReferenceById(user.getId());
        if(userBd != null && user.getNome() != null && user.getEmail() != null){
            
            userBd.setNome(user.getNome());
            userBd.setCpf(user.getCpf());
            userBd.setData_nascimento(user.getData_nascimento());
            userBd.setEmail(user.getEmail());
            userBd.setFone(user.getFone());
            usuarioRepository.save(userBd);
            return true;            
        } else {
            return false;
        }
    }  
    
    public Usuario consultaUsuario(Long id){
        
        if(id != null) {
            Optional<Usuario> user = usuarioRepository.findById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
        
    }

    
    
    
}
