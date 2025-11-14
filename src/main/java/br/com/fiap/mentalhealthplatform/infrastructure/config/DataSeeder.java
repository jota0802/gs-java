package br.com.fiap.mentalhealthplatform.infrastructure.config;

import br.com.fiap.mentalhealthplatform.domain.entity.Consulta;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import br.com.fiap.mentalhealthplatform.domain.entity.ProfissionalSaude;
import br.com.fiap.mentalhealthplatform.domain.entity.RegistroDiario;
import br.com.fiap.mentalhealthplatform.domain.enums.*;
import br.com.fiap.mentalhealthplatform.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            ProfissionalSaudeRepository profissionalRepository,
            PacienteRepository pacienteRepository,
            RegistroDiarioRepository registroRepository,
            ConsultaRepository consultaRepository) {
        
        return args -> {
            // Criar Profissionais de Saúde
            ProfissionalSaude psicologa1 = new ProfissionalSaude();
            psicologa1.setNome("Dra. Ana Silva");
            psicologa1.setEmail("ana.silva@mentalhealth.com");
            psicologa1.setTelefone("(11) 98765-4321");
            psicologa1.setCrp("06/123456");
            psicologa1.setTipoProfissional(TipoProfissional.PSICOLOGO);
            psicologa1.setEspecialidade("Terapia Cognitivo-Comportamental");
            psicologa1.setBio("Psicóloga especialista em TCC com 10 anos de experiência");
            psicologa1 = profissionalRepository.save(psicologa1);

            ProfissionalSaude psiquiatra1 = new ProfissionalSaude();
            psiquiatra1.setNome("Dr. Carlos Mendes");
            psiquiatra1.setEmail("carlos.mendes@mentalhealth.com");
            psiquiatra1.setTelefone("(11) 98765-1234");
            psiquiatra1.setCrp("06/654321");
            psiquiatra1.setTipoProfissional(TipoProfissional.PSIQUIATRA);
            psiquiatra1.setEspecialidade("Psiquiatria Clínica e Psicanálise");
            psiquiatra1.setBio("Psiquiatra com foco em transtornos de ansiedade e depressão");
            psiquiatra1 = profissionalRepository.save(psiquiatra1);

            ProfissionalSaude terapeuta1 = new ProfissionalSaude();
            terapeuta1.setNome("Maria Santos");
            terapeuta1.setEmail("maria.santos@mentalhealth.com");
            terapeuta1.setTelefone("(11) 98765-5678");
            terapeuta1.setCrp("06/789012");
            terapeuta1.setTipoProfissional(TipoProfissional.TERAPEUTA);
            terapeuta1.setEspecialidade("Mindfulness e Meditação");
            terapeuta1.setBio("Terapeuta especializada em técnicas de relaxamento e mindfulness");
            terapeuta1 = profissionalRepository.save(terapeuta1);

            // Criar Pacientes
            Paciente paciente1 = new Paciente();
            paciente1.setNome("João Pedro Oliveira");
            paciente1.setEmail("joao.oliveira@email.com");
            paciente1.setTelefone("(11) 91234-5678");
            paciente1.setCpf("12345678901");
            paciente1.setDataNascimento(LocalDate.of(1990, 5, 15));
            paciente1.setSexo("Masculino");
            paciente1.setObservacoes("Paciente com histórico de ansiedade");
            paciente1 = pacienteRepository.save(paciente1);

            Paciente paciente2 = new Paciente();
            paciente2.setNome("Maria Fernanda Costa");
            paciente2.setEmail("maria.costa@email.com");
            paciente2.setTelefone("(11) 91234-8765");
            paciente2.setCpf("98765432109");
            paciente2.setDataNascimento(LocalDate.of(1985, 8, 22));
            paciente2.setSexo("Feminino");
            paciente2.setObservacoes("Procura ajuda para gerenciamento de estresse");
            paciente2 = pacienteRepository.save(paciente2);

            Paciente paciente3 = new Paciente();
            paciente3.setNome("Pedro Henrique Santos");
            paciente3.setEmail("pedro.santos@email.com");
            paciente3.setTelefone("(11) 91234-9999");
            paciente3.setCpf("45678912301");
            paciente3.setDataNascimento(LocalDate.of(1995, 3, 10));
            paciente3.setSexo("Masculino");
            paciente3 = pacienteRepository.save(paciente3);

            // Criar Registros Diários
            RegistroDiario registro1 = new RegistroDiario();
            registro1.setPaciente(paciente1);
            registro1.setDataRegistro(LocalDate.now().minusDays(2));
            registro1.setNivelHumor(NivelHumor.BOM);
            registro1.setNivelAnsiedade(NivelAnsiedade.LEVE);
            registro1.setHorasSono(7);
            registro1.setPraticouExercicio(true);
            registro1.setQualidadeDia(8);
            registro1.setAnotacoes("Dia produtivo no trabalho");
            registroRepository.save(registro1);

            RegistroDiario registro2 = new RegistroDiario();
            registro2.setPaciente(paciente1);
            registro2.setDataRegistro(LocalDate.now().minusDays(1));
            registro2.setNivelHumor(NivelHumor.NEUTRO);
            registro2.setNivelAnsiedade(NivelAnsiedade.MODERADA);
            registro2.setHorasSono(5);
            registro2.setPraticouExercicio(false);
            registro2.setQualidadeDia(5);
            registro2.setAnotacoes("Dia estressante com prazos apertados");
            registro2.setGatilhosEmocionais("Pressão no trabalho");
            registroRepository.save(registro2);

            RegistroDiario registro3 = new RegistroDiario();
            registro3.setPaciente(paciente2);
            registro3.setDataRegistro(LocalDate.now().minusDays(1));
            registro3.setNivelHumor(NivelHumor.MUITO_BOM);
            registro3.setNivelAnsiedade(NivelAnsiedade.NENHUMA);
            registro3.setHorasSono(8);
            registro3.setPraticouExercicio(true);
            registro3.setQualidadeDia(9);
            registro3.setAnotacoes("Excelente dia, praticou yoga pela manhã");
            registroRepository.save(registro3);

            // Criar Consultas
            Consulta consulta1 = new Consulta();
            consulta1.setPaciente(paciente1);
            consulta1.setProfissional(psicologa1);
            consulta1.setDataHoraConsulta(LocalDateTime.now().plusDays(3).withHour(14).withMinute(0));
            consulta1.setStatus(StatusConsulta.AGENDADA);
            consulta1.setMotivoConsulta("Acompanhamento mensal - ansiedade");
            consulta1.setDuracaoMinutos(50);
            consulta1.setLocalConsulta("Consultório Av. Paulista, 1000");
            consulta1.setConsultaOnline(false);
            consultaRepository.save(consulta1);

            Consulta consulta2 = new Consulta();
            consulta2.setPaciente(paciente2);
            consulta2.setProfissional(psiquiatra1);
            consulta2.setDataHoraConsulta(LocalDateTime.now().plusDays(5).withHour(10).withMinute(0));
            consulta2.setStatus(StatusConsulta.CONFIRMADA);
            consulta2.setMotivoConsulta("Avaliação psiquiátrica inicial");
            consulta2.setDuracaoMinutos(60);
            consulta2.setConsultaOnline(true);
            consultaRepository.save(consulta2);

            Consulta consulta3 = new Consulta();
            consulta3.setPaciente(paciente1);
            consulta3.setProfissional(psicologa1);
            consulta3.setDataHoraConsulta(LocalDateTime.now().minusDays(7).withHour(15).withMinute(0));
            consulta3.setStatus(StatusConsulta.CONCLUIDA);
            consulta3.setMotivoConsulta("Sessão de terapia cognitivo-comportamental");
            consulta3.setObservacoesProfissional("Paciente demonstrou progresso significativo nas técnicas de respiração");
            consulta3.setDuracaoMinutos(50);
            consulta3.setLocalConsulta("Consultório Av. Paulista, 1000");
            consulta3.setConsultaOnline(false);
            consultaRepository.save(consulta3);

            Consulta consulta4 = new Consulta();
            consulta4.setPaciente(paciente3);
            consulta4.setProfissional(terapeuta1);
            consulta4.setDataHoraConsulta(LocalDateTime.now().plusDays(2).withHour(16).withMinute(30));
            consulta4.setStatus(StatusConsulta.AGENDADA);
            consulta4.setMotivoConsulta("Primeira sessão de mindfulness");
            consulta4.setDuracaoMinutos(45);
            consulta4.setConsultaOnline(true);
            consultaRepository.save(consulta4);

            System.out.println("✅ Dados iniciais carregados com sucesso!");
            System.out.println("📊 Total: " + profissionalRepository.count() + " profissionais, " 
                    + pacienteRepository.count() + " pacientes, "
                    + registroRepository.count() + " registros diários, "
                    + consultaRepository.count() + " consultas");
        };
    }
}
