package visao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import data.DateUtil;
import modelo.dao.ConsultaDao;
import modelo.dao.MedicoDao;
import modelo.dao.PacienteDao;
import modelo.entidade.Consulta;
import modelo.entidade.Medico;
import modelo.entidade.Paciente;

public class Principal {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		while (true) {
			exibirMenu();
			
			int opcao = teclado.nextInt();
			teclado.nextLine();
			
			if (opcao == 1) {
				break;
			} else if (opcao == 2) {
				Medico medico = new Medico();
				System.out.println("Informe o nome do médico:");
				medico.setNome(teclado.nextLine());
				
				System.out.println("Informe a matrícula do médico:");
				medico.setMatricula(teclado.nextInt());
				teclado.nextLine();
				
				System.out.println("Informe a especialidade do médico:");
				medico.setEspecialidade(teclado.nextLine());
				
				System.out.println("Informe o salário do médico:");
				medico.setSalario(teclado.nextFloat());
				
				
				MedicoDao medicoDao = new MedicoDao();
				medicoDao.cadastrarMedico(medico);
			} else if (opcao == 3) {
				Paciente paciente = new Paciente();
				
				System.out.println("Informe o nome do paciente:");
				paciente.setNome(teclado.nextLine());
				
				System.out.println("Informe o cpf do paciente:");
				paciente.setCpf(teclado.nextLine());
				
				System.out.println("Informe a doença do paciente:");
				paciente.setDoenca(teclado.nextLine());
				
				
				PacienteDao pacienteDao = new PacienteDao();
				pacienteDao.cadastrarPaciente(paciente);
			} else if (opcao == 4) {
				System.out.println("Insira a matrícula do médico:");
				int matricula = teclado.nextInt();
				
				
				MedicoDao medicoDao = new MedicoDao();
				Medico medico = medicoDao.buscarPorMatricula(matricula);
				
				if (medico != null) {
					System.out.println("Nome: " + medico.getNome());
					System.out.println("Matrícula: " + medico.getMatricula());
					System.out.println("Especialidade: " + medico.getEspecialidade());
					System.out.println("Salário: " + medico.getSalario());
				} else {
					System.out.println("O médico de matrícula " + matricula + " não está cadastrado!");
				}
			} else if (opcao == 5) {
				System.out.println("Informe o cpf do paciente:");
				String cpf = teclado.nextLine();
				
				
				PacienteDao pacienteDao = new PacienteDao();
				Paciente paciente = pacienteDao.buscarPorCpf(cpf);
				
				if (paciente != null) {
					System.out.println("Nome: " + paciente.getNome());
					System.out.println("CPF: " + paciente.getCpf());
					System.out.println("Doença: " + paciente.getDoenca());
				} else {
					System.out.println("O paciente de cpf " + cpf + " não está cadastrado!");
				}
			} else if (opcao == 6) {
				System.out.println("Informe a matrícula do médico:");
				int matricula = teclado.nextInt();
				teclado.nextLine();
				
				System.out.println("Informe o cpf do paciente:");
				String cpf = teclado.nextLine();
				
				System.out.println("Informe a data de ocorrência da consulta");
				String data = teclado.nextLine();
				
				System.out.println("Informe o horário da consulta:");
				String hora = teclado.nextLine();
				
				String dataHora = data + " " + hora + ":00";
				Object horario = DateUtil.stringToDate(dataHora, LocalDateTime.class);
				
				System.out.println("Informe o valor da consulta:");
				float valor = teclado.nextFloat();
				
				
				ConsultaDao consultaDao = new ConsultaDao();
				consultaDao.cadastrarConsulta(matricula, cpf, horario, valor);
			} else if (opcao == 7) {
				System.out.println("Informe a matrícula do médico:");
				int matricula = teclado.nextInt();
				teclado.nextLine();
				
				System.out.println("Informe o cpf do paciente:");
				String cpf = teclado.nextLine();
				
				System.out.println("Informe a data de ocorrência da consulta");
				String data = teclado.nextLine();
				
				System.out.println("Informe o horário da consulta:");
				String hora = teclado.nextLine();
				
				
				String dataHora = data + " " + hora + ":00";
				Object horario = DateUtil.stringToDate(dataHora, LocalDateTime.class);
				
				ConsultaDao consultaDao = new ConsultaDao();
				consultaDao.removerConsulta(matricula, cpf, horario);
			} else if (opcao == 8) {
				System.out.println("Informe a matrícula do médico:");
				int matricula = teclado.nextInt();
				teclado.nextLine();
				
				System.out.println("Informe o cpf do paciente:");
				String cpf = teclado.nextLine();
				
				System.out.println("Informe a data de ocorrência da consulta");
				String data = teclado.nextLine();
				
				System.out.println("Informe o horário da consulta:");
				String hora = teclado.nextLine();
				
				String dataHora = data + " " + hora + ":00";
				Object horario = DateUtil.stringToDate(dataHora, LocalDateTime.class);
				
				System.out.println("Informe a nova data de ocorrência da consulta");
				String dataNova = teclado.nextLine();
				
				System.out.println("Informe o novo horário da consulta:");
				String horaNova = teclado.nextLine();
				
				
				String dataHoraNova = dataNova + " " + horaNova + ":00";
				Object horarioNovo = DateUtil.stringToDate(dataHoraNova, LocalDateTime.class);
				
				ConsultaDao consultaDao = new ConsultaDao();
				consultaDao.atualizarHorarioConsulta(matricula, cpf, horario, horarioNovo);
			} else if (opcao == 9) {
				
				List<Consulta> consultas = new ConsultaDao().selecionarConsultas();
				
				for (int i = 0; i <= consultas.size()-1; i++) {
					System.out.println("Consulta " + (i+1));
					System.out.println();
					System.out.println("Nome do médico: " + consultas.get(i).getMedico().getNome() + " (" + consultas.get(i).getMedico().getMatricula() + ")");
					System.out.println("Nome do paciente: " + consultas.get(i).getPaciente().getNome() + " (" + consultas.get(i).getPaciente().getCpf() + ")");
					System.out.println("Horário: " + DateUtil.dateToString(consultas.get(i).getHorario()));
					System.out.printf("Valor: R$ %.2f", consultas.get(i).getValor());
					System.out.println();
					System.out.println();
				}
			} else {
				System.out.println("Opção inválida! Tente novamente!");
			}
		}
		
	}
	
	private static void exibirMenu() {
		System.out.println("----- GERENCIADOR DE PACIENTES -----");
		System.out.println();
		System.out.println("1 - Sair do programa");
		System.out.println("2 - Cadastrar novo médico");
		System.out.println("3 - Cadastrar novo paciente");
		System.out.println("4 - Buscar médico por matrícula");
		System.out.println("5 - Buscar paciente por CPF");
		System.out.println("6 - Cadastrar uma nova consulta");
		System.out.println("7 - Remover uma consulta cadastrada");
		System.out.println("8 - Atualizar o horário de uma consulta cadastrada");
		System.out.println("9 - Gerar relatório de consultas");
		System.out.println();
		System.out.println("Escolha uma opção válida:");
	}
	
}
