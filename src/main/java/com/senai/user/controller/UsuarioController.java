
package com.senai.user.controller;

import com.senai.user.entity.Usuario;
import com.senai.user.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/usuario")
    public ResponseEntity<Object> listaUsuarios(HttpServletRequest request){
        
        System.out.println("Host cliente: " + request.getRemoteHost() + "\n" +
                           "IP cliente: " + request.getRemoteAddr() + "\n" +
                           "Porta cliente: " + request.getRemotePort());
        List<Usuario> listaUser = usuarioService.listarUsuarios();
        if( !listaUser.isEmpty() ){
            return new ResponseEntity<>(listaUser,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object> consultaUsuario(@Valid @PathVariable(value = "id") Long id  ){
        
        Usuario user = usuarioService.consultaUsuario(id);
        if( user != null ){
            return new ResponseEntity<>(user,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> excluirtaUsuario( @Valid @PathVariable(value = "id") Long id  ){
        
       if( id != null ){
           usuarioService.excluirUsuario(id);
           return new ResponseEntity<>("Usuário excluído!! ",HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    @PutMapping("/usuario")
    public ResponseEntity<Object> alterarUsuario( @Valid @RequestBody Usuario user  ){
        
        if(user.getId() != null){
            usuarioService.alterarUsuario(user);
            return new ResponseEntity<>("Dados Usuário Atualizados!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
    @PostMapping("/usuario")
    public ResponseEntity<Object> incluirNovoUsuario( @Valid @RequestBody Usuario user  ){
        
        if(user.getNome() != null && user.getEmail() != null){
            Long id = usuarioService.inserirNovoUsuario(user);
            return new ResponseEntity<>("Inserido Usuário id="+ id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
    }
    
}
