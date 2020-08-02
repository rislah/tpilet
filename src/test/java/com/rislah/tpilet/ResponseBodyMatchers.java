package com.rislah.tpilet;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.error.CustomJsonError;
import com.rislah.tpilet.error.ValidationError;

public class ResponseBodyMatchers {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public static ResponseBodyMatchers responseBody() {
		return new ResponseBodyMatchers();
	}

	public ResultMatcher containsValidationError(String key, String message) {
		return mvcResult -> {
			String str = mvcResult.getResponse().getContentAsString();
			CustomJsonError error = objectMapper.readValue(str, CustomJsonError.class);
			List<ValidationError> validationErrors = error.getValidationErrors().stream()
					.filter(fieldError -> fieldError.getKey().equals(key))
					.filter(fieldError -> fieldError.getError().equals(message)).collect(Collectors.toList());

			assertThat(validationErrors).withFailMessage(
					"expecting exactly error message with key '%s' and message '%s'", key, message).hasSize(1);
		};
	}
}
