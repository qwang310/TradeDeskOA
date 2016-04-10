package unitTest;


import assignment.service.ValidateService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Test validation rules
 */
public class ValidateServiceTest {

	private ValidateService validateService;


	@Before
	public void setUp() {
		this.validateService = new ValidateService();
	}

	@Test
	public void shouldValidateRuleOne(){
		Boolean test1 = validateService.FirstRule("friend1");
		assertThat(test1, is(true));

		Boolean test2 = validateService.FirstRule("fri1231312311313end");
		assertThat(test2, is(false));

		Boolean test3 = validateService.FirstRule("fri1");
		assertThat(test3, is(false));


	}

	@Test
	public void shouldValidateRuleTwo() {
		Boolean test1 = validateService.SecondRule("friend");
		assertThat(test1, is(false));

		Boolean test2 = validateService.SecondRule("friendbbb");
		assertThat(test2, is(false));

		Boolean test3 = validateService.SecondRule("friend13F");
		assertThat(test3, is(false));

		Boolean test4 = validateService.SecondRule("*&^%$#9f");
		assertThat(test4, is(false));

		Boolean test5 = validateService.SecondRule("bacddj7");
		assertThat(test5, is(true));


	}

	@Test
	public void shouldValidateRuleThree(){
		Boolean test1 = validateService.ThirdRuleSecond("aaf00f00ff");
		assertThat(test1, is(false));

		Boolean test2 = validateService.ThirdRuleSecond("foola2foo");
		assertThat(test2, is(true));

		Boolean test3 = validateService.ThirdRuleSecond("foo1foo1");
		assertThat(test3, is(false));

		Boolean test4 = validateService.ThirdRuleSecond("foolfoo4foo");
		assertThat(test4, is(true));

		Boolean test5 = validateService.ThirdRuleSecond("aaaa");
		assertThat(test5, is(false));

	}

	@Test
	public void shouldValidatePassword(){
		Boolean test1 = validateService.Validate("ab");
		assertThat(test1, is(false));

		Boolean test2 = validateService.Validate("foolafoo1");
		assertThat(test2, is(true));

		Boolean test3 = validateService.Validate("foofoo");
		assertThat(test3, is(false));

		Boolean test4 = validateService.Validate("14#4");
		assertThat(test4, is(false));
	}

}