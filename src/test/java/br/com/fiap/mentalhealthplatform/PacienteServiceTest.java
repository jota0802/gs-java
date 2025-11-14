package br.com.fiap.mentalhealthplatform;

import br.com.fiap.mentalhealthplatform.application.dto.PacienteRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.PacienteResponseDTO;
import br.com.fiap.mentalhealthplatform.application.exception.DuplicateResourceException;
import br.com.fiap.mentalhealthplatform.application.exception.ResourceNotFoundException;
import br.com.fiap.mentalhealthplatform.application.service.PacienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @DisplayName("Deve criar um paciente com sucesso")
    void deveCriarPacienteComSucesso() {
        // Arrange
        PacienteRequestDTO dto = new PacienteRequestDTO();
        dto.setNome("Teste Silva");
        dto.setEmail("teste@email.com");
        dto.setTelefone("(11) 98765-4321");
        dto.setCpf("11111111111");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));
        dto.setSexo("Masculino");

        // Act
        PacienteResponseDTO response = pacienteService.create(dto);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("Teste Silva", response.getNome());
        assertEquals("teste@email.com", response.getEmail());
        assertEquals("11111111111", response.getCpf());
        assertTrue(response.getAtivo());
    }

    @Test
    @DisplayName("Não deve criar paciente com email duplicado")
    void naoDeveCriarPacienteComEmailDuplicado() {
        // Arrange
        PacienteRequestDTO dto1 = new PacienteRequestDTO();
        dto1.setNome("Teste 1");
        dto1.setEmail("duplicado@email.com");
        dto1.setTelefone("(11) 98765-4321");
        dto1.setCpf("11111111111");
        dto1.setDataNascimento(LocalDate.of(1990, 1, 1));

        PacienteRequestDTO dto2 = new PacienteRequestDTO();
        dto2.setNome("Teste 2");
        dto2.setEmail("duplicado@email.com");
        dto2.setTelefone("(11) 98765-4322");
        dto2.setCpf("22222222222");
        dto2.setDataNascimento(LocalDate.of(1991, 1, 1));

        // Act
        pacienteService.create(dto1);

        // Assert
        assertThrows(DuplicateResourceException.class, () -> {
            pacienteService.create(dto2);
        });
    }

    @Test
    @DisplayName("Não deve criar paciente com CPF duplicado")
    void naoDeveCriarPacienteComCpfDuplicado() {
        // Arrange
        PacienteRequestDTO dto1 = new PacienteRequestDTO();
        dto1.setNome("Teste 1");
        dto1.setEmail("email1@email.com");
        dto1.setTelefone("(11) 98765-4321");
        dto1.setCpf("33333333333");
        dto1.setDataNascimento(LocalDate.of(1990, 1, 1));

        PacienteRequestDTO dto2 = new PacienteRequestDTO();
        dto2.setNome("Teste 2");
        dto2.setEmail("email2@email.com");
        dto2.setTelefone("(11) 98765-4322");
        dto2.setCpf("33333333333");
        dto2.setDataNascimento(LocalDate.of(1991, 1, 1));

        // Act
        pacienteService.create(dto1);

        // Assert
        assertThrows(DuplicateResourceException.class, () -> {
            pacienteService.create(dto2);
        });
    }

    @Test
    @DisplayName("Deve buscar paciente por ID")
    void deveBuscarPacientePorId() {
        // Arrange
        PacienteRequestDTO dto = new PacienteRequestDTO();
        dto.setNome("Busca Teste");
        dto.setEmail("busca@email.com");
        dto.setTelefone("(11) 98765-4321");
        dto.setCpf("44444444444");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));

        PacienteResponseDTO created = pacienteService.create(dto);

        // Act
        PacienteResponseDTO found = pacienteService.findById(created.getId());

        // Assert
        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
        assertEquals("Busca Teste", found.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar paciente inexistente")
    void deveLancarExcecaoAoBuscarPacienteInexistente() {
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pacienteService.findById(99999L);
        });
    }

    @Test
    @DisplayName("Deve atualizar paciente com sucesso")
    void deveAtualizarPacienteComSucesso() {
        // Arrange
        PacienteRequestDTO createDto = new PacienteRequestDTO();
        createDto.setNome("Nome Original");
        createDto.setEmail("original@email.com");
        createDto.setTelefone("(11) 98765-4321");
        createDto.setCpf("55555555555");
        createDto.setDataNascimento(LocalDate.of(1990, 1, 1));

        PacienteResponseDTO created = pacienteService.create(createDto);

        PacienteRequestDTO updateDto = new PacienteRequestDTO();
        updateDto.setNome("Nome Atualizado");
        updateDto.setEmail("original@email.com");
        updateDto.setTelefone("(11) 98765-9999");
        updateDto.setCpf("55555555555");
        updateDto.setDataNascimento(LocalDate.of(1990, 1, 1));

        // Act
        PacienteResponseDTO updated = pacienteService.update(created.getId(), updateDto);

        // Assert
        assertNotNull(updated);
        assertEquals("Nome Atualizado", updated.getNome());
        assertEquals("(11) 98765-9999", updated.getTelefone());
    }

    @Test
    @DisplayName("Deve listar todos os pacientes")
    void deveListarTodosPacientes() {
        // Act
        List<PacienteResponseDTO> pacientes = pacienteService.findAll();

        // Assert
        assertNotNull(pacientes);
        assertTrue(pacientes.size() > 0);
    }

    @Test
    @DisplayName("Deve inativar paciente")
    void deveInativarPaciente() {
        // Arrange
        PacienteRequestDTO dto = new PacienteRequestDTO();
        dto.setNome("Inativar Teste");
        dto.setEmail("inativar@email.com");
        dto.setTelefone("(11) 98765-4321");
        dto.setCpf("66666666666");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));

        PacienteResponseDTO created = pacienteService.create(dto);

        // Act
        pacienteService.inativar(created.getId());

        // Assert
        PacienteResponseDTO inativo = pacienteService.findById(created.getId());
        assertFalse(inativo.getAtivo());
    }
}
