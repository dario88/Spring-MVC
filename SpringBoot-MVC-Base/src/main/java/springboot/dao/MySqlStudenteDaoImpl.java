package springboot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import springboot.model.Studente;

@Repository
@Qualifier("mysql_db")
public class MySqlStudenteDaoImpl implements StudenteDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class StudenteRowMapper implements RowMapper<Studente> {

		@Override
		public Studente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Studente studente = new Studente();
			studente.setId(resultSet.getInt("id"));
			studente.setNome(resultSet.getString("nome"));
			studente.setCognome(resultSet.getString("cognome"));
			studente.setEsame(resultSet.getString("esame"));
			return studente;
		}
		
	}

	@Override
	public Collection<Studente> getAllStudenti() {
		final String sql = "SELECT id, nome, cognome, esame FROM studenti";
		List<Studente> studenti = jdbcTemplate.query(sql, new StudenteRowMapper());
		return studenti;
	}

	@Override
	public Studente getStudenteById(int id) {
		// TODO Auto-generated method stub
		final String sql = "SELECT id, nome, cognome, esame FROM studenti where id = ?";
		Studente studente = jdbcTemplate.queryForObject(sql, new StudenteRowMapper(), id);
		return studente;
	}

	@Override
	public void removeStudenteById(int id) {
		// TODO Auto-generated method stub
		final String sql = "DELETE FROM studenti where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public void updateStudente(Studente studente) {
		// TODO Auto-generated method stub
		final String sql = "UPDATE studenti SET nome = ?, cognome = ?, esame = ? where id = ?";
		final int id = studente.getId();
		final String nome = studente.getNome();
		final String cognome = studente.getCognome();
		final String esame = studente.getEsame();
		jdbcTemplate.update(sql, new Object[] {nome, cognome, esame, id});
		
	}

	@Override
	public void insertStudente(Studente studente) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO studenti (nome, cognome, esame) VALUES (?, ?, ?)";
		final String nome = studente.getNome();
		final String cognome = studente.getCognome();
		final String esame = studente.getEsame();
		jdbcTemplate.update(sql, new Object[] {nome, cognome, esame});
	}

}
