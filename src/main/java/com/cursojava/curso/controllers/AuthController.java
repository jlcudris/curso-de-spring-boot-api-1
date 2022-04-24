package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTutil  jwtutil;

    @RequestMapping(value ="api/login",method = RequestMethod.POST)
    public String iniciarSesion(@RequestBody Usuario usuario) {

        Usuario usuarioLog =  usuarioDao.obtenerUsuariosPorCredenciales(usuario);
        if(usuarioLog !=null){

            return jwtutil.create(String.valueOf(usuarioLog.getId()),usuarioLog.getEmail());

        }

        return  "FAIL";
    }
}
