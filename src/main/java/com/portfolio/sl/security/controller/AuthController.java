package com.portfolio.sl.security.controller;

import com.portfolio.sl.security.Dto.JwtDTO;
import com.portfolio.sl.security.Dto.LoginUsuario;
import com.portfolio.sl.security.Dto.NuevoUser;
import com.portfolio.sl.security.entity.Rol;
import com.portfolio.sl.security.entity.User;
import com.portfolio.sl.security.enums.RolName;
import com.portfolio.sl.security.jwt.JwtProvider;
import com.portfolio.sl.security.service.RolService;
import com.portfolio.sl.security.service.UserService;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUser nuevoUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);

        if (userService.existsByNombreUser(nuevoUser.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);

        if (userService.existsByEmail(nuevoUser.getEmail()))
            return new ResponseEntity(new Mensaje("Ese email de usuario ya existe"), HttpStatus.BAD_REQUEST);

        User user = new User(nuevoUser.getNombre(), nuevoUser.getNombreUsuario(),               
                nuevoUser.getEmail(), passwordEncoder.encode(nuevoUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
           roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (nuevoUser.getRoles().contains("admin")) {
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
            user.setRoles(roles);
            userService.save(user);

            return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal completados"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
