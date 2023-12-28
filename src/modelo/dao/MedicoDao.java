package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidade.Medico;

public class MedicoDao extends AbstratoDao{
	
	public void cadastrarMedico(Medico medico) {
		String comandoSql = "insert into medico(nome, matricula, especialidade, salario) values (?, ?, ?, ?)";
		
		try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			pstm.setString(1, medico.getNome());
			pstm.setInt(2, medico.getMatricula());
			pstm.setString(3, medico.getEspecialidade());
			pstm.setFloat(4, medico.getSalario());
			
			if (pstm.executeUpdate() == 1) {
				System.out.println("O médico foi cadastrado com sucesso!");
			} else {
				System.out.println("Não foi possível cadastrar o médico!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Medico buscarPorMatricula(int matricula) {
		Medico medico = null;
		String comandoSql = "select * from medico where matricula = ?";
		
		try (PreparedStatement pstm = conexao.prepareStatement(comandoSql)) {
			pstm.setInt(1, matricula);
			
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				medico = new Medico();
				
				medico.setNome(rs.getString("nome"));
				medico.setMatricula(rs.getInt("matricula"));
				medico.setEspecialidade(rs.getString("especialidade"));
				medico.setSalario(rs.getFloat("salario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medico;
	}
	
}
