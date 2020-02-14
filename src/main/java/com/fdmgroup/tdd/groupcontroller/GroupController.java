package com.fdmgroup.tdd.groupcontroller;

import java.util.HashMap;
import java.util.Map;

public class GroupController implements GroupControllerService {

	private DatabaseReader databaseReader;
	private DatabaseWriter databaseWriter;

	public GroupController() {
		databaseReader = new DatabaseReader();
		databaseWriter = new DatabaseWriter();

	}

	public GroupController(DatabaseReader databaseReader, DatabaseWriter databaseWriter) {
		this.databaseReader = databaseReader;
		this.databaseWriter = databaseWriter;
	}

	@Override
	public Map<String, Trainee> getAllTrainees() {
		Map<String, Trainee> allTrainees = databaseReader.readGroup();
		if (allTrainees == null) {
			allTrainees = new HashMap<String, Trainee>();
		}
		return allTrainees;
	}

	@Override
	public void addTrainee(Trainee trainee) {
		if (trainee == null) {
			return;
		}
		databaseWriter.addTrainee(trainee);
	}

	@Override
	public void removeTraineeByUsername(String traineeUsername) {
		if (traineeUsername == null || traineeUsername.isEmpty()) {
			return;
		}
		databaseWriter.deleteTraineeByUsername(traineeUsername);

	}

}
