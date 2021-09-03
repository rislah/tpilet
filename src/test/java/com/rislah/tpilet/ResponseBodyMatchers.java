package com.rislah.tpilet;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiErrorResponse;
import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiFieldError;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.error.CustomErrorResponse;
import com.rislah.tpilet.error.ValidationError;

public class ResponseBodyMatchers {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public static ResponseBodyMatchers responseBody() {
		return new ResponseBodyMatchers();
	}

	public ResultMatcher containsValidationError(String key, String message) {
		return mvcResult -> {
			String str = mvcResult.getResponse().getContentAsString();
			System.out.println(str);
			ApiErrorResponse error = objectMapper.readValue(str, ApiErrorResponse.class);
			List<ApiFieldError> validationErrors = error.getFieldErrors().stream()
					.filter(fieldError -> fieldError.getProperty().equals(key))
					.filter(fieldError -> fieldError.getMessage().equals(message)).collect(Collectors.toList());

			assertThat(validationErrors).withFailMessage(
					"expecting exactly error message with key '%s' and message '%s'", key, message).hasSizeGreaterThan(0);
		};
	}
}
