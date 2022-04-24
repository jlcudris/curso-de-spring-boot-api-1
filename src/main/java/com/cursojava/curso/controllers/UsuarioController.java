package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;

import com.cursojava.curso.utils.JWTutil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    //hace referencia la mapeo de la url del controlador
    @Autowired
    private UsuarioDao usuarioDao ;
    @Autowired
    private JWTutil jwTutil;

    @RequestMapping(value ="api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario(
                id,
                "maria","flozrez",
                "12345678","mp-20@hotmail.com","123456");
                return usuario;
    }

    //guardar token para validarlo
    @RequestMapping(value ="api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        if(!validarToken(token)){
            return null;
        }
       return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){

        String usuario_id = jwTutil.getKey(token);
        return usuario_id != null;
    }

    @RequestMapping(value = "api/usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id,@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){
            return ;
        }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value ="api/usuarios",method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash= argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);

         usuarioDao.registrarUsuarios(usuario);
    }



}
