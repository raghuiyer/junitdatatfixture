package org.raghuiyer.datafixture;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.raghuiyer.datafixture.FixturedTest;
import org.raghuiyer.datafixture.JacksonDataFixture;

public class SimpleObjectTest {
	
	@Rule
	public JacksonDataFixture<SimpleObject> simpleObjectFixture = new JacksonDataFixture<>(SimpleObject.class);

	@Test
	@FixturedTest
	public void  testSimpleObjectJSONLoad() {
		assertEquals("Samuel", this.simpleObjectFixture.getFixturedObject().getName().getFirst());
		assertEquals("Vimes", this.simpleObjectFixture.getFixturedObject().getName().getLast());
	}
}
