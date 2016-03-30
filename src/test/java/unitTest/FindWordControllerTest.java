package unitTest;


import assignment.controller.FindWordController;
import assignment.dao.Result;
import assignment.service.ProcessingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class FindWordControllerTest {

	private FindWordController findWordController;

	private ProcessingService processingService;

	private HashMap<String, Result> map = new HashMap<String, Result>();

	private String testFilePath = "src/test/resources/txtfiles/";


	@Before
	public void setUp() {
		this.findWordController = new FindWordController();
		this.processingService = new ProcessingService();
		this.processingService.setTxtfilesPath(testFilePath);
		this.processingService.setMap(map);
		this.findWordController.setProcessingService(processingService);

	}

	@Test
	public void shouldCountNumberOfRequest() throws Exception {

		/* Test Number of request when word "Paris" been search the first time */
		ResponseEntity<String> responseEntity = findWordController.findWord("Paris");
		String resultString = responseEntity.getBody();
		assertThat(resultString, notNullValue());
		assertThat(resultString, is("For word 'Paris', the number of request is 1, and the number of occurences is 0"));


		/* Test Number of request when word "Paris" been search the second time */
		responseEntity = findWordController.findWord("Paris");
		resultString = responseEntity.getBody();
		assertThat(resultString, notNullValue());
		assertThat(resultString, is("For word 'Paris', the number of request is 2, and the number of occurences is 0"));

		/* Test Number of request when word "Paris" been search the third time */
		responseEntity = findWordController.findWord("Paris");
		resultString = responseEntity.getBody();
		assertThat(resultString, notNullValue());
		assertThat(resultString, is("For word 'Paris', the number of request is 3, and the number of occurences is 0"));

	}

	/* Return warning when entering an invalid Word */
	@Test
	public void shouldFailForInvalidInput() throws Exception {

		ResponseEntity<String> responseEntity = findWordController.findWord("Friend's");
		String resultString = responseEntity.getBody();
		assertThat(resultString, notNullValue());
		assertThat(resultString, is("Not a valid English word"));

		responseEntity = findWordController.findWord("ha ha");
		resultString = responseEntity.getBody();
		assertThat(resultString, notNullValue());
		assertThat(resultString, is("Not a valid English word"));

		responseEntity = findWordController.findWord("bad-word");
		resultString = responseEntity.getBody();
		assertThat(resultString, notNullValue());
		assertThat(resultString, is("Not a valid English word"));

	}

}