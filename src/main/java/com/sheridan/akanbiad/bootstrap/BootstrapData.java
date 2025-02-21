package com.sheridan.akanbiad.bootstrap;


import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sheridan.akanbiad.beans.Dog;

import com.sheridan.akanbiad.beans.Judge;
import com.sheridan.akanbiad.beans.Owner;


import com.sheridan.akanbiad.repositories.DogRepository;

import com.sheridan.akanbiad.repositories.JudgeRepository;
import com.sheridan.akanbiad.repositories.OwnerRepository;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

	private DogRepository dogRepo;
	private JudgeRepository judgeRepo;
	private OwnerRepository ownerRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Judge j1 = Judge.builder().name("Judge 1").build();
		Judge j2 = Judge.builder().name("Judge 2").build();
		Judge j3 = Judge.builder().name("Judge 3").build();
		
		Owner o1 = Owner.builder().firstname("Owner 1 firstname").lastname("Owner 1 lastname").build();
		Owner o2 = Owner.builder().firstname("Owner 2 firstname").lastname("Owner 2 lastname").build();
		Owner o3 = Owner.builder().firstname("Owner 3 firstname").lastname("Owner 3 lastname").build();
		Owner o4 = Owner.builder().firstname("Owner 4 firstname").lastname("Owner 4 lastname").build();
		Owner o5 = Owner.builder().firstname("Owner 5 firstname").lastname("Owner 5 lastname").build();
		Owner o6 = Owner.builder().firstname("Owner 6 firstname").lastname("Owner 6 lastname").build();
		
		judgeRepo.save(j1);
		judgeRepo.save(j2);
		judgeRepo.save(j3);
	
		
		ownerRepo.save(o1);
		ownerRepo.save(o2);
		ownerRepo.save(o3);
		ownerRepo.save(o4);
		ownerRepo.save(o5);
		ownerRepo.save(o6);
		

		
		Owner[] owners = {o1,o2,o3,o4,o5,o6};
		Judge[] judges = {j1,j2,j3};
		
		for (int i = 1; i <= 30; i++) {
			Dog d = Dog.builder().name("Dog " + i).build();
			d.setOwner(owners[i%6]);
			d.setJudges(new ArrayList<Judge>());
			d.getJudges().add(judges[i%2]);
			d.getJudges().add(judges[2]);
			dogRepo.save(d);
			
		}
		
		
		judgeRepo.save(j1);
		judgeRepo.save(j2);
		judgeRepo.save(j3);
	
		
		ownerRepo.save(o1);
		ownerRepo.save(o2);
		ownerRepo.save(o3);
		ownerRepo.save(o4);
		ownerRepo.save(o5);
		ownerRepo.save(o6);
		
		
		
	}

}
