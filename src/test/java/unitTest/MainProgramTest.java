package unitTest;


import assignment.dao.Result;
import assignment.service.ProcessingService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class MainProgramTest {

	private ProcessingService processingService;

	private HashMap<String, Result> map = new HashMap<String, Result>();

	public String testFilePath = "src/test/resource/txtfiles/";


	@Before
	public void setUp() {
		this.processingService = new ProcessingService();
		this.processingService.setTxtfilesPath(testFilePath);
		this.processingService.setMap(map);

	}

	@Test
	public void shouldFindWord() throws Exception {
		Result result = processingService.countWord("Paris");

		int occurrences = result.getNumberOfOccurrences();

		assertThat(result, notNullValue());
		assertThat(occurrences, is(12));

	}

}