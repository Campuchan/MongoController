package eu.campuchan.MongoController;

import eu.campuchan.MongoController.model.Usuario;
import eu.campuchan.MongoController.repository.UsuarioRepository;
import eu.campuchan.MongoController.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testFindByNombre() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("pepe");
        when(usuarioRepository.findByNombre("pepe")).thenReturn(usuario);

        // Act
        Usuario result = usuarioService.findByNombre("pepe");

        // Assert
        assertNotNull(result);
        assertEquals("pepe", result.getNombre());
    }
}