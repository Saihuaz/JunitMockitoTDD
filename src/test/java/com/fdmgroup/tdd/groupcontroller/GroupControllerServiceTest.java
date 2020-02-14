package com.fdmgroup.tdd.groupcontroller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GroupControllerServiceTest {

	private DatabaseReader mockDatabaseReader;
	private DatabaseWriter mockDatabaseWriter;
	private GroupController groupController;

	@Before
	public void set() {
		mockDatabaseReader = mock(DatabaseReader.class);
		mockDatabaseWriter = mock(DatabaseWriter.class);
		groupController = new GroupController(mockDatabaseReader, mockDatabaseWriter);
	}

	@After
	public void colector() {
		groupController = null;
	}

	@Test
	public void test_GetAllTrainee_ReturnEmptyMap_NoTraineesWereAdded() {

		Map<String, Trainee> allTrainees = groupController.getAllTrainees();

		assertTrue(allTrainees.isEmpty());

	}

	@Test
	public void test_GetAllTrainee_CallsDatabaseReader_ReadGroupOnce() {

		@SuppressWarnings("unused")
		Map<String, Trainee> unusedAllTrainees = groupController.getAllTrainees();

		verify(mockDatabaseReader, times(1)).readGroup();
		verifyNoMoreInteractions(mockDatabaseReader);
	}

	@Test
	public void test_AddTrainee_CallsDatabaseWriterAddTrainee_JustOnce_MockingTrainee() {
		Trainee mockTrainee = mock(Trainee.class);

		groupController.addTrainee(mockTrainee);

		verify(mockDatabaseWriter, times(1)).addTrainee(mockTrainee);
		verifyNoMoreInteractions(mockDatabaseWriter);
	}

	@Test
	public void test_AddTrainee_NoCallToDatabaseWriterAddTrainee_NoTraineeSpecifiedToAdd() {
		Trainee trainee = null;

		groupController.addTrainee(trainee);

		verify(mockDatabaseWriter, times(0)).addTrainee(trainee);
		verifyNoMoreInteractions(mockDatabaseWriter);

	}

	@Test
	public void test_RemoveTraineeByName_CallsDatabaseWriterRemoveTraineeByUserame_TraineeUsernameRemoved() {
		String traineeUsername = "Sai";

		groupController.removeTraineeByUsername(traineeUsername);

		verify(mockDatabaseWriter, times(1)).deleteTraineeByUsername(traineeUsername);
		verifyNoMoreInteractions(mockDatabaseWriter);
	}

	@Test
	public void test_RemoveTraineeByName_DoesNotCallDatabaseWriterRemoveTraineeByUsername_NoTraineeUsernameGiven() {
		String traineeUsername = "";

		groupController.removeTraineeByUsername(traineeUsername);

		verify(mockDatabaseWriter, times(0)).deleteTraineeByUsername(traineeUsername);
		verifyNoMoreInteractions(mockDatabaseWriter);
	}

	@Test
	public void test_RemoveTraineeByName_DoesNotCallDatabaseWriterRemoveTraineeByUsername_TraineeUsernameIsEmpty() {
		String traineeUsername = null;

		groupController.removeTraineeByUsername(traineeUsername);

		verify(mockDatabaseWriter, times(0)).deleteTraineeByUsername(traineeUsername);
		verifyNoMoreInteractions(mockDatabaseWriter);
	}

	//Not a great test as it relies on the result of an external method
	//Since there is no trainee data in the map, this test is somewhat redundant
	@Test
	public void test_RemoveTraineeByName_KeyNotPresent_WhenNamedTraineeIsDeleted() {

		groupController.removeTraineeByUsername("Sai");

		Map<String, Trainee> allTrainees = groupController.getAllTrainees();

		assertFalse(allTrainees.containsKey("Sai"));

	}

}
