package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidade.Paciente;

public class PacienteDao extends AbstratoDao{
	
	public void cadastrarPaciente(Paciente paciente) {
		String comandoSql = "insert into paciente(nome, cpf, doenca) values (?, ?, ?)";
		
		try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			pstm.setString(1, paciente.getNome());
			pstm.setString(2, paciente.getCpf());
			pstm.setString(3, paciente.getDoenca());
			
			if (pstm.executeUpdate() == 1) {
				System.out.println("Paciente cadastrado com sucesso!");
			} else {
				System.out.println("Não foi possível cadastrar o paciente!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Paciente buscarPorCpf(String cpf) {
		Paciente paciente = null;
		String comandoSql = "select * from paciente where cpf = ?";
		
		 try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			 pstm.setString(1, cpf);
			 
			 ResultSet rs = pstm.executeQuery();
			 if (rs.next()) {
				 paciente = new Paciente();
				 
				 paciente.setNome(rs.getString("nome"));
				 paciente.setCpf(rs.getString("cpf"));
				 paciente.setDoenca(rs.getString("doenca"));
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		
		return paciente;
	}
	
}
