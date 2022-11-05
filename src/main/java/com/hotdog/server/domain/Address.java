package com.hotdog.server.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable

public class Address {
	private String address; // 지번주소

	private String roadAddress; //도로명주소

	private String zipCode; // 우편번호
	//카카오 map api 사용할거임

}
