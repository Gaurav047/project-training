package com.sapient.customer.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse<T> {

	private T message;
}
