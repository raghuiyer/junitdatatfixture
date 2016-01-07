package org.raghuiyer.datafixture;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDataFixture<T> extends AbstractDataFixture<T> {
	private ObjectMapper objectMapper = new ObjectMapper();
	private Class<T> typeRef;
	
	public JacksonDataFixture(Class<T> typeRef, Module... modules) {
		this.objectMapper.registerModules(modules);
		this.typeRef = typeRef;
	}

	@Override
	protected T loadData(String fixtureFilePath) {
		InputStream src = null;
		try {
			src = this.getClass().getClassLoader().getResourceAsStream(fixtureFilePath);
			return this.objectMapper.readValue(src, typeRef);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(src != null) {
				try {
					src.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
