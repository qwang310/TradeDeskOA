package unitTest;


import assignment.dao.Result;
import assignment.service.ProcessingService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/*
Some sample tests to test how to count occurrences of the word in the files

 */
public class MainProgramTest {

	private ProcessingService processingService;

	private ConcurrentHashMap<String, Result> map = new ConcurrentHashMap<String, Result>();

	private String testFilePath = "src/test/resources/txtfiles/";


	@Before
	public void setUp() {
		this.processingService = new ProcessingService();
		this.processingService.setTxtfilesPath(testFilePath);
		this.processingService.setMap(map);

	}

	@Test
	public void shouldFindWord() throws Exception {
		Result result = processingService.countWord("friend");

		int occurrences = result.getNumberOfOccurrences();

		assertThat(result, notNullValue());
		assertThat(occurrences, is(15));



		result = processingService.countWord("Friend");
		occurrences = result.getNumberOfOccurrences();

		assertThat(result, notNullValue());
		assertThat(occurrences, is(5));


		result = processingService.countWord("friends");
		occurrences = result.getNumberOfOccurrences();

		assertThat(result, notNullValue());
		assertThat(occurrences, is(15));

	}

}