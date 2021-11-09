package com.ferguson.cs.loadbalancer.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@SpringBootTest
@WireMockTest(httpPort = 8090)
class HelloFeignClientTest {

    @Autowired
    HelloFeignClient client;

    @Test
    void defaultHello() {
		stubFor(get(urlEqualTo("/hello")).willReturn(aResponse().withBody("Hello, World!")));
        assertEquals("Hello, World!", client.hello());
    }

	@Test
	void helloUsername() {
		stubFor(get(urlEqualTo("/hello/Rik")).willReturn(aResponse().withBody("Hello, Rik!")));
		assertEquals("Hello, Rik!", client.hello("Rik"));
	}

	@Test
	void helloFormattedDate() {
		stubFor(post(urlEqualTo("/hello")).withRequestBody(equalToJson("\"2021-11-03T12:34:56.000-0400\""))
				.willReturn(aResponse().withBody("Hello, Rik!")));

		ZonedDateTime date = ZonedDateTime.of(2021, 11, 3, 12, 34, 56, 0, ZoneOffset.of("-04:00"));
		assertEquals("Hello, Rik!", client.helloDate(Date.from(date.toInstant())));
	}

}
