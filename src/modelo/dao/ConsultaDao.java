package modelo.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidade.Consulta;
import modelo.entidade.Medico;
import modelo.entidade.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultaDao extends AbstratoDao{
	
	public void cadastrarConsulta(int matricula, String cpf, Object horario, float valor) {
		String comandoSql = "insert into consulta(id_medico, id_paciente, horario, valor) values((select id from medico where matricula = ?), (select id from paciente where cpf = ?), ?, ?)";
		
		try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			pstm.setInt(1, matricula);
			pstm.setString(2, cpf);
			pstm.setObject(3, horario);
			pstm.setFloat(4, valor);
			
			if(pstm.executeUpdate() == 1) {
				System.out.println("Consulta cadastrada com sucesso!");
			} else {
				System.out.println("Não foi possível cadastrar essa consulta!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerConsulta(int matricula, String cpf, Object horario) {
		String comandoSql = "delete from consulta where id_medico = (select id from medico where matricula = ?)"
				+ "and id_paciente = (select id from paciente where cpf = ?)"
				+ "and horario = ?";
		
		try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			pstm.setInt(1, matricula);
			pstm.setString(2, cpf);
			pstm.setObject(3, horario);
			
			if (pstm.executeUpdate() == 1) {
				System.out.println("A consulta foi removida com sucesso!");
			} else {
				System.out.println("Não foi possível remover a consulta. Verifique as informações "
						+ "e tente novamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarHorarioConsulta(int matricula, String cpf, Object horario, Object dataHoraNova) {
		String comandoSql = "update consulta set horario = ? where id_medico = (select id from medico where matricula = ?)"
				+ "and id_paciente = (select id from paciente where cpf = ?) and horario = ?";
		
		try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			pstm.setObject(1, dataHoraNova);
			pstm.setInt(2, matricula);
			pstm.setString(3, cpf);
			pstm.setObject(4, horario);
			
			if (pstm.executeUpdate() == 1) {
				System.out.println("O horário da consulta foi alterado com sucesso!");
			} else {
				System.out.println("Não foi possível modificar o horário. Verifique as informações "
						+ "e tente novamente!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Consulta> selecionarConsultas() {
		List<Consulta> consultas = null;
		String comandoSql = "select medico.nome, medico.matricula, paciente.nome, paciente.cpf, consulta.horario, consulta.valor "
				+ "from consulta inner join medico on consulta.id_medico = medico.id "
				+ "inner join paciente on consulta.id_paciente = paciente.id";
		
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(comandoSql)) {
			consultas = new ArrayList<Consulta>();
			
			while (rs.next()) {
				Consulta consulta = new Consulta();
				
				Medico medico = new Medico();
				medico.setNome(rs.getString("medico.nome"));
				medico.setMatricula(rs.getInt("medico.matricula"));
				consulta.setMedico(medico);
				
				Paciente paciente = new Paciente();
				paciente.setNome(rs.getString("paciente.nome"));
				paciente.setCpf(rs.getString("paciente.cpf"));
				consulta.setPaciente(paciente);
				
				consulta.setHorario(rs.getObject("consulta.horario"));
				consulta.setValor(rs.getFloat("consulta.valor"));
				
				consultas.add(consulta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}
	
}
