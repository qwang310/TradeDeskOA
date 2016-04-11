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

		Boolean test2 = validateService.FirstRule("fri1234567890end");
		assertThat(test2, is(false));

		Boolean test3 = validateService.FirstRule("fri1");
		assertThat(test3, is(false));


	}

	@Test
	public void shouldValidateRuleTwo() {
		Boolean test1 = validateService.SecondRule("friend");
		assertThat(test1, is(false));

		Boolean test3 = validateService.SecondRule("friend13F");
		assertThat(test3, is(false));

		Boolean test4 = validateService.SecondRule("*&^%$#9f");
		assertThat(test4, is(false));

		Boolean test5 = validateService.SecondRule("bacdej7");
		assertThat(test5, is(true));


	}

	@Test
	public void shouldValidateRuleThree(){
		Boolean test1 = validateService.ThirdRule("af0bf0bfaf");
		assertThat(test1, is(false));

		Boolean test2 = validateService.ThirdRule("foala2foa");
		assertThat(test2, is(true));

		Boolean test3 = validateService.ThirdRule("foo1foo1");
		assertThat(test3, is(false));

		Boolean test4 = validateService.ThirdRule("foclfoc4foc");
		assertThat(test4, is(true));

		Boolean test5 = validateService.ThirdRule("aaaa");
		assertThat(test5, is(false));

		Boolean test6 = validateService.ThirdRule("bccu");
		assertThat(test6, is(false));

	}

	@Test
	public void shouldValidateRuleThreeSecondWay(){
		Boolean test1 = validateService.ThirdRuleSecondWay("af0bf0bfaf");
		assertThat(test1, is(false));

		Boolean test2 = validateService.ThirdRuleSecondWay("foala2foa");
		assertThat(test2, is(true));

		Boolean test3 = validateService.ThirdRuleSecondWay("foo1foo1");
		assertThat(test3, is(false));

		Boolean test4 = validateService.ThirdRuleSecondWay("foclfoc4foc");
		assertThat(test4, is(true));

		Boolean test5 = validateService.ThirdRuleSecondWay("aaaa");
		assertThat(test5, is(false));

		Boolean test6 = validateService.ThirdRuleSecondWay("bccu");
		assertThat(test6, is(false));

	}

	@Test
	public void shouldValidateMixedRules(){
		Boolean test1 = validateService.Validate("ab");
		assertThat(test1, is(false));

		Boolean test2 = validateService.Validate("foofoo");
		assertThat(test2, is(false));

		Boolean test3 = validateService.Validate("14#4");
		assertThat(test3, is(false));

		Boolean test4 = validateService.Validate("Franklin8");
		assertThat(test4, is(false));

		Boolean test5 = validateService.Validate("foalafoa1");
		assertThat(test5, is(true));

		Boolean test6 = validateService.Validate("abcdef123");
		assertThat(test6, is(true));

		Boolean test7 = validateService.Validate("67ac89hcb");
		assertThat(test7, is(true));
	}

}