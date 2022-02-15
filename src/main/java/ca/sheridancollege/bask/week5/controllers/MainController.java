package ca.sheridancollege.bask.week5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.bask.week5.beans.Player;
import ca.sheridancollege.bask.week5.database.DatabaseAccess;

@Controller
public class MainController {
	
	@Autowired
	private DatabaseAccess da;
	
	//created for creating the bean and getting the team database
	@GetMapping("/newPlayer")
	public String newPlayerForm(Model model) {
		
		model.addAttribute("player", new Player());
		model.addAttribute("teams", da.getTeams());

		return "newPlayer.html";
	}
	
	//created for adding the new player to the database
	@PostMapping("/addPlayer")
	public String addPLayer(Model model, @ModelAttribute Player player) {
		
		da.addPlayer(player);

		return "results.html" ;
	}
	
	
	@GetMapping("/listPlayers")
	public String listPlayers(Model model) {
		//get all the players
		model.addAttribute("players", da.getPlayers());
		
		return "playerList.html";
	}
	
	//created for search by name
	@GetMapping("/search")
	public String searchPlayers() {
		return "searchPlayer.html";
	}
	
	//created for search by name. returns the player list
	@GetMapping("/searchName")
	public String searchByName(Model model, @RequestParam String search) {
		
		model.addAttribute("players", da.searchByNames(search));
		return "playerList.html";
	}
	
	//created for path variables(links)
	@GetMapping("/viewPlayers")
	public String viewPlayers(Model model) {
		
		model.addAttribute("teams", da.getTeams());
		
		return "playersByTeam.html";
		
	}
	
	//created for path variable(links)
	@GetMapping("/viewTeam/{teamId}")
	public String viewTeam(Model model, @PathVariable String teamId) {
		
		model.addAttribute("players", da.searchByTeam(teamId));
		model.addAttribute("teams", da.getTeams());
		
		return "playersByTeam.html";
	}
	
	

	
	
	
	
	
	
	
	
//	@GetMapping("/results")
//	public String result(Model model) {
//		model.addAttribute("players", da.getPlayer());
//		model.addAttribute("team", da.getTeams());
//		return "results.html";
//	}
//	

//	
//	@GetMapping("/list")
//	public String goToList(Model model) {
//		
//		model.addAttribute("list", da.getPlayer());
//		return "playerList.html";
//	}
//	
////	@GetMapping("/search")
////	public String search(Model model,@ModelAttribute Player player,
////			@RequestParam String searchValue) {
////		
////		model.addAttribute("search", da.findPlayer(searchValue));
////		return "playerList.html";
////	}
//	
//	@GetMapping("/viewPlayers")
//	public String viewPlayers(Model model) {
//		
//		model.addAttribute("teams", da.getTeams());
//		
//		return "playersByTeam";
//	}
//	
//	@GetMapping("/viewTeam/{teamId}")
//	public String viewTeam(Model model, @PathVariable String teamId) {
//		
//		model.addAttribute("players", da.searchByTeams(teamId));
//		model.addAttribute("teams", da.getTeams());
//		
//		return "playersByTeam.html";
//	}
//	
//	@GetMapping("/editPlayer/{id}")
//	public String editPlayer(Model model, @PathVariable int id) {
//		
//		Player p =da.getPlayer(id);
//		model.addAttribute("player", p);
//		
//		
//		return "newPlayer.html";
//	}
//	@GetMapping("/deletePlayer/{id}")
//	public String deletePlayer(Model model, @PathVariable int id) {
//		
//		Player p =da.getPlayer(id);
//		model.addAttribute("player", p);
//		
//		
//		return "newPlayer.html";
//	}
}

