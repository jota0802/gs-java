package br.com.fiap.mentalhealthplatform.presentation.controller;

import br.com.fiap.mentalhealthplatform.application.dto.ConsultaRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ConsultaResponseDTO;
import br.com.fiap.mentalhealthplatform.application.dto.PacienteRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.PacienteResponseDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeResponseDTO;
import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioResponseDTO;
import br.com.fiap.mentalhealthplatform.application.service.ConsultaService;
import br.com.fiap.mentalhealthplatform.application.service.PacienteService;
import br.com.fiap.mentalhealthplatform.application.service.ProfissionalSaudeService;
import br.com.fiap.mentalhealthplatform.application.service.RegistroDiarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller para servir páginas HTML usando Thymeleaf
 * Substitui a abordagem anterior de JavaScript/API REST para renderização server-side
 */
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ProfissionalSaudeService profissionalSaudeService;
    private final PacienteService pacienteService;
    private final RegistroDiarioService registroDiarioService;
    private final ConsultaService consultaService;

    /**
     * Página inicial - Dashboard com estatísticas
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activePage", "dashboard");
        
        // Carregar dados para o dashboard
        var profissionais = profissionalSaudeService.findAll();
        var pacientes = pacienteService.findAll();
        var registros = registroDiarioService.findAll();
        var consultas = consultaService.findAll();
        
        // Adicionar contadores ao model
        model.addAttribute("totalProfissionais", profissionais.size());
        model.addAttribute("totalPacientes", pacientes.size());
        model.addAttribute("totalRegistros", registros.size());
        model.addAttribute("totalConsultas", consultas.size());
        
        // Últimas consultas (top 5)
        var ultimasConsultas = consultas.stream()
                .sorted((c1, c2) -> c2.getDataHoraConsulta().compareTo(c1.getDataHoraConsulta()))
                .limit(5)
                .toList();
        model.addAttribute("ultimasConsultas", ultimasConsultas);
        
        // Últimos pacientes cadastrados (top 5)
        var ultimosPacientes = pacientes.stream()
                .sorted((p1, p2) -> p2.getDataCadastro().compareTo(p1.getDataCadastro()))
                .limit(5)
                .toList();
        model.addAttribute("ultimosPacientes", ultimosPacientes);
        
        return "index";
    }

    /**
     * Página de listagem de profissionais de saúde
     */
    @GetMapping("/profissionais")
    public String profissionais(Model model) {
        model.addAttribute("activePage", "profissionais");
        var profissionais = profissionalSaudeService.findAll();
        model.addAttribute("profissionais", profissionais);
        return "profissionais";
    }

    /**
     * Página de listagem de pacientes
     */
    @GetMapping("/pacientes")
    public String pacientes(Model model) {
        model.addAttribute("activePage", "pacientes");
        var pacientes = pacienteService.findAll();
        model.addAttribute("pacientes", pacientes);
        return "pacientes";
    }

    /**
     * Página de registros diários
     */
    @GetMapping("/registros")
    public String registros(Model model) {
        model.addAttribute("activePage", "registros");
        var registros = registroDiarioService.findAll();
        var pacientes = pacienteService.findAll(); // Para filtro/dropdown
        model.addAttribute("registros", registros);
        model.addAttribute("pacientes", pacientes);
        return "registros";
    }

    /**
     * Página de consultas
     */
    @GetMapping("/consultas")
    public String consultas(Model model) {
        model.addAttribute("activePage", "consultas");
        var consultas = consultaService.findAll();
        var pacientes = pacienteService.findAll(); // Para filtro/dropdown
        var profissionais = profissionalSaudeService.findAll(); // Para filtro/dropdown
        model.addAttribute("consultas", consultas);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("profissionais", profissionais);
        return "consultas";
    }

    // ==== FORMULÁRIOS DE CRIAÇÃO ====

    /**
     * Formulário de cadastro de novo paciente
     */
    @GetMapping("/pacientes/novo")
    public String novoPacienteForm(Model model) {
        model.addAttribute("activePage", "pacientes");
        return "novo-paciente";
    }

    /**
     * Processar criação de novo paciente
     */
    @PostMapping("/pacientes/novo")
    public String criarPaciente(@ModelAttribute PacienteRequestDTO dto, RedirectAttributes redirectAttributes) {
        pacienteService.create(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente cadastrado com sucesso!");
        return "redirect:/pacientes";
    }

    /**
     * Formulário de cadastro de novo profissional
     */
    @GetMapping("/profissionais/novo")
    public String novoProfissionalForm(Model model) {
        model.addAttribute("activePage", "profissionais");
        return "novo-profissional";
    }

    /**
     * Processar criação de novo profissional
     */
    @PostMapping("/profissionais/novo")
    public String criarProfissional(@ModelAttribute ProfissionalSaudeRequestDTO dto, RedirectAttributes redirectAttributes) {
        profissionalSaudeService.create(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Profissional de saúde cadastrado com sucesso!");
        return "redirect:/profissionais";
    }

    /**
     * Formulário de agendamento de nova consulta
     */
    @GetMapping("/consultas/nova")
    public String novaConsultaForm(Model model) {
        model.addAttribute("activePage", "consultas");
        var pacientes = pacienteService.findAll();
        var profissionais = profissionalSaudeService.findAll();
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("profissionais", profissionais);
        return "nova-consulta";
    }

    /**
     * Processar criação de nova consulta
     */
    @PostMapping("/consultas/nova")
    public String criarConsulta(@ModelAttribute ConsultaRequestDTO dto, RedirectAttributes redirectAttributes) {
        consultaService.create(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Consulta agendada com sucesso!");
        return "redirect:/consultas";
    }

    /**
     * Formulário de cadastro de novo registro
     */
    @GetMapping("/registros/novo")
    public String novoRegistroForm(Model model) {
        model.addAttribute("activePage", "registros");
        var pacientes = pacienteService.findAll();
        model.addAttribute("pacientes", pacientes);
        return "novo-registro";
    }

    /**
     * Processar criação de novo registro
     */
    @PostMapping("/registros/novo")
    public String criarRegistro(@ModelAttribute RegistroDiarioRequestDTO dto, RedirectAttributes redirectAttributes) {
        registroDiarioService.create(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Registro diário cadastrado com sucesso!");
        return "redirect:/registros";
    }

    // ==== FORMULÁRIOS DE EDIÇÃO ====

    /**
     * Formulário de edição de paciente
     */
    @GetMapping("/pacientes/editar/{id}")
    public String editarPacienteForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "pacientes");
        var paciente = pacienteService.findById(id);
        model.addAttribute("paciente", paciente);
        return "editar-paciente";
    }

    /**
     * Processar edição de paciente
     */
    @PostMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, @ModelAttribute PacienteRequestDTO dto, RedirectAttributes redirectAttributes) {
        pacienteService.update(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente atualizado com sucesso!");
        return "redirect:/pacientes";
    }

    /**
     * Formulário de edição de profissional
     */
    @GetMapping("/profissionais/editar/{id}")
    public String editarProfissionalForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "profissionais");
        var profissional = profissionalSaudeService.findById(id);
        model.addAttribute("profissional", profissional);
        return "editar-profissional";
    }

    /**
     * Processar edição de profissional
     */
    @PostMapping("/profissionais/editar/{id}")
    public String editarProfissional(@PathVariable Long id, @ModelAttribute ProfissionalSaudeRequestDTO dto, RedirectAttributes redirectAttributes) {
        profissionalSaudeService.update(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Profissional de saúde atualizado com sucesso!");
        return "redirect:/profissionais";
    }

    /**
     * Formulário de edição de consulta
     */
    @GetMapping("/consultas/editar/{id}")
    public String editarConsultaForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "consultas");
        var consulta = consultaService.findById(id);
        var pacientes = pacienteService.findAll();
        var profissionais = profissionalSaudeService.findAll();
        model.addAttribute("consulta", consulta);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("profissionais", profissionais);
        return "editar-consulta";
    }

    /**
     * Processar edição de consulta
     */
    @PostMapping("/consultas/editar/{id}")
    public String editarConsulta(@PathVariable Long id, @ModelAttribute ConsultaRequestDTO dto, RedirectAttributes redirectAttributes) {
        consultaService.update(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Consulta atualizada com sucesso!");
        return "redirect:/consultas";
    }

    /**
     * Formulário de edição de registro
     */
    @GetMapping("/registros/editar/{id}")
    public String editarRegistroForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "registros");
        var registro = registroDiarioService.findById(id);
        var pacientes = pacienteService.findAll();
        model.addAttribute("registro", registro);
        model.addAttribute("pacientes", pacientes);
        return "editar-registro";
    }

    /**
     * Processar edição de registro
     */
    @PostMapping("/registros/editar/{id}")
    public String editarRegistro(@PathVariable Long id, @ModelAttribute RegistroDiarioRequestDTO dto, RedirectAttributes redirectAttributes) {
        registroDiarioService.update(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Registro diário atualizado com sucesso!");
        return "redirect:/registros";
    }

    // ==== EXCLUSÃO ====

    /**
     * Confirmar exclusão de paciente
     */
    @GetMapping("/pacientes/excluir/{id}")
    public String confirmarExclusaoPaciente(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "pacientes");
        PacienteResponseDTO paciente = pacienteService.findById(id);
        model.addAttribute("entidade", paciente);
        model.addAttribute("tipo", "paciente");
        model.addAttribute("nome", paciente.getNome());
        model.addAttribute("urlVoltar", "/pacientes");
        return "confirmar-exclusao";
    }

    /**
     * Processar exclusão de paciente
     */
    @PostMapping("/pacientes/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pacienteService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente excluído com sucesso!");
        return "redirect:/pacientes";
    }

    /**
     * Confirmar exclusão de profissional
     */
    @GetMapping("/profissionais/excluir/{id}")
    public String confirmarExclusaoProfissional(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "profissionais");
        ProfissionalSaudeResponseDTO profissional = profissionalSaudeService.findById(id);
        model.addAttribute("entidade", profissional);
        model.addAttribute("tipo", "profissional");
        model.addAttribute("nome", profissional.getNome());
        model.addAttribute("urlVoltar", "/profissionais");
        return "confirmar-exclusao";
    }

    /**
     * Processar exclusão de profissional
     */
    @PostMapping("/profissionais/excluir/{id}")
    public String excluirProfissional(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        profissionalSaudeService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Profissional de saúde excluído com sucesso!");
        return "redirect:/profissionais";
    }

    /**
     * Confirmar exclusão de consulta
     */
    @GetMapping("/consultas/excluir/{id}")
    public String confirmarExclusaoConsulta(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "consultas");
        var consulta = consultaService.findById(id);
        model.addAttribute("entidade", consulta);
        model.addAttribute("tipo", "consulta");
        model.addAttribute("nome", "Consulta #" + id);
        model.addAttribute("urlVoltar", "/consultas");
        return "confirmar-exclusao";
    }

    /**
     * Processar exclusão de consulta
     */
    @PostMapping("/consultas/excluir/{id}")
    public String excluirConsulta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        consultaService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Consulta excluída com sucesso!");
        return "redirect:/consultas";
    }

    /**
     * Confirmar exclusão de registro
     */
    @GetMapping("/registros/excluir/{id}")
    public String confirmarExclusaoRegistro(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "registros");
        var registro = registroDiarioService.findById(id);
        model.addAttribute("entidade", registro);
        model.addAttribute("tipo", "registro");
        model.addAttribute("nome", "Registro #" + id);
        model.addAttribute("urlVoltar", "/registros");
        return "confirmar-exclusao";
    }

    /**
     * Processar exclusão de registro
     */
    @PostMapping("/registros/excluir/{id}")
    public String excluirRegistro(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        registroDiarioService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Registro diário excluído com sucesso!");
        return "redirect:/registros";
    }
}
