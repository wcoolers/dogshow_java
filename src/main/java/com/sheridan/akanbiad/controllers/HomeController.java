package com.sheridan.akanbiad.controllers;

import java.util.Optional;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sheridan.akanbiad.beans.Dog;
import com.sheridan.akanbiad.beans.Judge;
import com.sheridan.akanbiad.beans.Owner;
import com.sheridan.akanbiad.repositories.DogRepository;
import com.sheridan.akanbiad.repositories.JudgeRepository;
import com.sheridan.akanbiad.repositories.OwnerRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private DogRepository dogRepo;
	private OwnerRepository ownerRepo;
	private JudgeRepository judgeRepo;
	

	
	//ADD
	@GetMapping("/") 
	public String iAmRoot() {
		return "home.html";
	}
	
	@GetMapping("/add") 
	public String goToAddPage(Model model) { 
		
		model.addAttribute("dog", new Dog());
		model.addAttribute("owner", new Owner());
		model.addAttribute("judge", new Judge());
		
		return "add.html";
	}
	

	@PostMapping("/addDog") 
	public String processDog(@ModelAttribute Dog dog) {
		dogRepo.save(dog);	
		
		return "redirect:/add"; 
	}
	
	@PostMapping("/addOwner") 
	public String processOwner(@ModelAttribute Owner owner) {
		
		ownerRepo.save(owner);		
		return "redirect:/add"; 
	}
	
	@PostMapping("/addJudge") 
	public String processJudge(@ModelAttribute Judge judge) {
		
		judgeRepo.save(judge);
		return "redirect:/add"; 
	}
	
	

	//VIEW DOGS
	@GetMapping("/viewDogs")
	public String getViewDogsPage(Model model) {

		model.addAttribute("dogs", dogRepo.findAll());
		return "viewDogs.html";
	}
	
	
	//VIEW DOGS DETAILS
	@GetMapping("/dogDetails/{id}")
	public String getDogsDetails(@PathVariable Long id, Model model) {
		Optional<Dog> dog = dogRepo.findById(id);
		
		if (dog.isPresent()) { 
			Dog selectedDog = dog.get();
			model.addAttribute("dog", selectedDog);

			return "viewDogDetails.html";
			
		} else { 
			return "redirect:/viewDogs";
		}
		

	}
	
	//REMOVE JUDGE FROM DOG
	@GetMapping("/removeJudgeFromDog/{dogId}/{judgeId}")
	public String removeJudgeFromDog(@PathVariable Long dogId, @PathVariable Long judgeId) {
		Optional<Dog> optionalDog = dogRepo.findById(dogId);
	    Optional<Judge> optionalJudge = judgeRepo.findById(judgeId);

	    if (optionalDog.isPresent() && optionalJudge.isPresent()) {
	        Dog dog = optionalDog.get();
	        Judge judge = optionalJudge.get();

	        // Remove the judge from the dog's list
	        dog.getJudges().remove(judge);

	        // Save the updated dog entity
	        dogRepo.save(dog);
	    }
	    return "redirect:/dogDetails/{dogId}";

	}
	
	
	//VIEW OWNERS
	@GetMapping("/viewOwners")
	public String getViewOwnersPage(Model model) {

		model.addAttribute("owners", ownerRepo.findAll());
		return "viewOwners.html";
	}
	
	
	//VIEW OWNER DETAILS
	@GetMapping("/ownerDetails/{id}")
	public String getOwnersDetails(@PathVariable Long id, Model model) {
		Optional<Owner> owner = ownerRepo.findById(id);
		
		if (owner.isPresent()) { 
			Owner selectedOwner = owner.get();

			model.addAttribute("owner", selectedOwner);

			return "viewOwnerDetails.html";
			
		} else { 
			return "redirect:/viewOwners";
		}
		

	}
	
	//VIEW JUDGES
	@GetMapping("/viewJudges")
	public String getViewJudgesPage(Model model) {

		model.addAttribute("judges", judgeRepo.findAll());
		return "viewJudges.html";
	}
	
	
	//VIEW JUDGE DETAILS
	@GetMapping("/judgeDetails/{id}")
	public String getJudgesDetails(@PathVariable Long id, Model model) {
		Optional<Judge> judge = judgeRepo.findById(id);
		
		if (judge.isPresent()) { 
			Judge selectedJudge = judge.get();

			model.addAttribute("judge", selectedJudge);

			return "viewJudgeDetails.html";
			
		} else { 
			return "redirect:/viewJudges";
		}
		

	}
	
	//ASSIGN PAGE
	@GetMapping("/assign")
	public String getAssignPage(Model model) {

		model.addAttribute("judges", judgeRepo.findAll());
		model.addAttribute("dogs", dogRepo.findAll());
		model.addAttribute("owners", ownerRepo.findAll());
		
		return "assign.html";
	}
	
	
	//Assign Judge to Dog
	@GetMapping("/assignJudgeToDog")
	public String processJudgeAssignment(@RequestParam Long dogId, @RequestParam Long judgeId) {
		
		System.out.println(dogId);
		System.out.println(judgeId);
		Optional<Dog> optionalDog = dogRepo.findById(dogId);
	    Optional<Judge> optionalJudge = judgeRepo.findById(judgeId);

	    if (optionalDog.isPresent() && optionalJudge.isPresent()) {
	        Dog dog = optionalDog.get();
	        Judge judge = optionalJudge.get();

	        // Remove the judge from the dog's list
	        dog.getJudges().add(judge);

	        // Save the updated dog entity
	        dogRepo.save(dog);
	    }
	    return "redirect:/dogDetails/" + dogId;
		

	}
	
	//Assign Owner to Dog
	@GetMapping("/assignOwnerToDog")
	public String processOwnerAssignment(@RequestParam Long dogId, @RequestParam Long ownerId) {
		Optional<Dog> optionalDog = dogRepo.findById(dogId);
	    Optional<Owner> optionalOwner = ownerRepo.findById(ownerId);

	    if (optionalDog.isPresent() && optionalOwner.isPresent()) {
	        Dog dog = optionalDog.get();
	        Owner owner = optionalOwner.get();

	        // Remove the judge from the dog's list
	        dog.setOwner(owner);

	        // Save the updated dog entity
	        dogRepo.save(dog);
	    }
	    return "redirect:/dogDetails/" + dogId;
		

	}
	
//	@PostMapping("/edit") //localhost:8080/add
//	public String processEdit(@ModelAttribute Videogame videogame) {
//		
//		//saves to the database
//		vgRepo.save(videogame);
//		
//		
//		return "redirect:/view"; //makes a new get request to the "/view" endpoint.
//	}
//	
//	//DELETE
//	@GetMapping("/removeJudgeFromDog/{id}")
//	public String deleteGame(@PathVariable int id) {
//		
//		vgRepo.deleteById(id); // if the id is not found, the method ignores it.
//		return "redirect:/view";
//
//	}
//
//	
//	
//	//SEARCH
//	
//	@GetMapping("/search") //localhost:8080/search
//	public String goToSearch() {
//		return "search.html";
//	}
//	
//	
//	@GetMapping("/searchTitle")
//	public String searchTitle(Model model, @RequestParam String title) {
//
//		model.addAttribute("videogames", vgRepo.findByTitleContainingIgnoreCase(title));
//		return "search.html";
//
//	}
//	
//	@GetMapping("/searchPriceLessThan")
//	public String searchPrice(Model model, @RequestParam double price) {
//
//		model.addAttribute("videogames", vgRepo.findByPriceLessThanEqual(price));
//		return "search.html";
//
//	}
	

}