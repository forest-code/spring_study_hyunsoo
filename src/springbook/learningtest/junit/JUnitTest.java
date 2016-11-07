package springbook.learningtest.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.either;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/junit.xml")
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTest {

	@Autowired
	ApplicationContext context;

	/* First Test */
	static JUnitTest testObject;

	/* Second Test */
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

	/* Third Test */
	static ApplicationContext contextObject = null;

	@Test
	public void test1() {
		System.out.println("before first-test1 : " + testObject);
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
		System.out.println("after first-test1 : " + testObject);
		System.out.println();

		System.out.println("before second-test1 : " + testObjects);
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		System.out.println("after second-test1 : " + testObjects);
		System.out.println();

		System.out.println("before third-test1 : " + contextObject);
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
		System.out.println("after third-test1 : " + contextObject);
		System.out.println();
	}

	@Test
	public void test2() {
		System.out.println("before first-test2 : " + testObject);
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
		System.out.println("after first-test2 : " + testObject);
		System.out.println();

		System.out.println("before second-test2 : " + testObjects);
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		System.out.println("after second-test2 : " + testObjects);
		System.out.println();

		System.out.println("before third-test2 : " + contextObject);
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
		System.out.println("after third-test2 : " + contextObject);
		System.out.println();
	}

	@Test
	public void test3() {
		/* First Test */
		System.out.println("before first-test3 : " + testObject);
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
		System.out.println("after first-test3 : " + testObject);
		System.out.println();

		System.out.println("before second-test3 : " + testObjects);
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		System.out.println("after second-test3 : " + testObjects);
		System.out.println();

		System.out.println("before third-test3 : " + contextObject);
		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		contextObject = this.context;
		System.out.println("after third-test3 : " + contextObject);
		System.out.println();
	}

}
