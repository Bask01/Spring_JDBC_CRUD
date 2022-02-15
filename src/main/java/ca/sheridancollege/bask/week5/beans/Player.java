package ca.sheridancollege.bask.week5.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
    private String firstName;
    private String lastName;
    private int number;
    private String team;
	/**
	 * @param firstName
	 * @param lastName
	 * @param number
	 * @param team
	 */
	public Player(String first, String last, int number, String team) {
		id=0;
		setFirstName(first);
		setLastName(last);
		setNumber(number);
		setTeam(team);
	}
    
    

}
