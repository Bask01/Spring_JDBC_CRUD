package ca.sheridancollege.bask.week5.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bask.week5.beans.Player;
import ca.sheridancollege.bask.week5.beans.Team;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void addPlayer(Player player) {
		
		String sql = "INSERT INTO players (firstName, lastName, number, team)"
				+ "VALUES (:first, :last, :number, :team);";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("first", player.getFirstName()).addValue("last", player.getLastName())
			.addValue("number", player.getNumber()).addValue("team", player.getTeam());
		
		jdbc.update(sql, params);
	}
	
	
	
	public List<Team> getTeams(){
		
		String sql = "SELECT * FROM teams ORDER BY city;";
		
		
		//We can use this method only if our bean variable names are exactly same with the table variable names!!!
		ArrayList<Team> teams =(ArrayList<Team>)jdbc.query(sql, 
				new BeanPropertyRowMapper<Team>(Team.class));
		return teams;
		
		
		//We can use this method as well if our bean variable names are not same with the table variable names!
		/*
		 * List<Team> teams = new ArrayList<Team>();
		 * 
		 * //List<Map<columnName type, valueType>> 
		 * List<Map<String, Object>> rows = jdbc.queryForList(sql, new HashMap());
		 * 
		 * for(Map<String, Object> row : rows) { 
		 * Team team = new Team(); 
		 * team.setId((String)(row.get("id")));
		 * team.setCity((String)(row.get("city")));
		 * team.setName((String)(row.get("name")));
		 * 
		 * teams.add(team);
		 * 
		 * }
		 */
	}
	
	public List<Player> getPlayer() {
		//Select all players sorted by name
		String sql = "Select * FROM players";
		
		//execute the query
		ArrayList<Player> players =(ArrayList<Player>)jdbc.query(sql,
				new BeanPropertyRowMapper<Player>(Player.class));
		
		return players;
	}
	

	
	//retrieve a List of all the players
			public List<Player> getPlayers() {
				
			
			String sql = "SELECT * FROM players ORDER BY lastName, firstName;";
			
			//execute query
			ArrayList<Player> players = (ArrayList<Player>) jdbc.query(sql, 
					new BeanPropertyRowMapper<Player>(Player.class));
			
	  		return players;
		}
			
			
			public List<Player> searchByNames(String searchValue) {
				
				//:first and :last values are kept in the searchValue
				String sql = "SELECT * FROM players WHERE firstName LIKE :first "
						+ "OR lastName LIKE :last ORDER BY lastName, firstName;";
				
				MapSqlParameterSource params = new MapSqlParameterSource();
				
				params.addValue("first", "%" + searchValue + "%")
					.addValue("last", "%" + searchValue + "%");
					
				ArrayList<Player> players = (ArrayList<Player>)jdbc.query(sql, params, 
						new BeanPropertyRowMapper<Player>(Player.class));
				return players;
			}
			
			
			public List<Player> searchByTeam(String... team) {
				
				String sql = "SELECT * FROM players WHERE team IN (";
				MapSqlParameterSource params = new MapSqlParameterSource();
				
				for (int i=0; i < team.length-1; i++) {
					String param = "team" + i;
					params.addValue(param,  team[i]);
					sql += ":" + param + "," ;
				}
				sql += ":team" + (team.length -1) + ");";
				params.addValue("team" + (team.length -1) , team[team.length-1]);
				
				ArrayList<Player> players = (ArrayList<Player>) jdbc.query(sql, params,
						new BeanPropertyRowMapper<Player>(Player.class));
				return players;
				 
			}
			
}
	
	
	
	
	
	
	
	
	
	
	
	


