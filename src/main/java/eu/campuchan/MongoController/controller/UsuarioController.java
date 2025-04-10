package eu.campuchan.MongoController.controller;

import eu.campuchan.MongoController.exception.DuplicateEmailException;
import eu.campuchan.MongoController.model.Usuario;
import eu.campuchan.MongoController.dto.UsuarioDTO;
import eu.campuchan.MongoController.repository.UsuarioRepository;
import eu.campuchan.MongoController.service.ImageService;
import eu.campuchan.MongoController.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping()
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios().stream()
                .map(usuario -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setNombre(usuario.getNombre());
                    dto.setCorreo(usuario.getCorreo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     * multipart/form-data
     *
     * @param nombreUsuario
     * @param password
     * @param correo
     * @param imagen
     * @param nombre
     * @return
     */
    @PostMapping(name = "", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<?> createUsuario(@RequestParam("imagen") MultipartFile imagen,
                                           @RequestParam("nombreUsuario") String nombreUsuario,
                                           @RequestParam("password") String password,
                                           @RequestParam("correo") String correo,
                                           @RequestParam("nombre") String nombre) {
        logger.info("Creating a new user with email: {}", correo);
        try {
            Usuario usuario = new Usuario(nombreUsuario ,nombre, correo, password);
            String imageUrl = imageService.saveImageToStorage(imagen);
            usuario.setFotoUrl(imageUrl);
            Usuario createdUsuario = usuarioService.createUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
        } catch (DuplicateEmailException e) {
            logger.error("Error creando usuario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error creando usuario: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable String id, @RequestParam("file") MultipartFile file, @RequestParam("usuario") Usuario usuario) {
        logger.info("Updating user with id: {}", id);
        try {
            String imageUrl = imageService.saveImageToStorage(file);
            usuario.setFotoUrl(imageUrl);
            Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (Exception e) {
            logger.error("Error updating user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
