package com.market;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.repository.CostumerRepository;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.utils.WebClientUtil;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTests {

	@Autowired
	private WebClientUtil webClientUtil;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected CostumerRepository costumerRepository;

	@Autowired
	protected RuleMarketPlaceRepository ruleMarketPlaceRepository;

		// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	protected WebTestClient webTestClient;

//	@MockBean
//	protected ConsumerClient consumerClient;

//	@MockBean
//	protected SellerClient commonSellerClient;

//	@MockBean
//	protected AddressClient addressClient;

	protected void clearDatabase() {
		ruleMarketPlaceRepository.deleteAll();
		costumerRepository.deleteAll();
	}

//	protected void createStubForFindConsumerAddressByIds(Address expectedAddress) {
//		Mockito.when(addressClient.getConsumerAddressByIds(Collections.singleton(expectedAddress.getId())))
//				.thenReturn(Collections.singletonList(expectedAddress));
//	}

//	protected void createStubForGetCity(City city) {
//		Mockito.when(addressClient.getCity(city.getId()))
//				.thenReturn(city);
//	}

//	protected void createStubForGetConsumer(Consumer consumer) {
//		Mockito.when(consumerClient.get(consumer.getId()))
//				.thenReturn(consumer);
//	}

	protected MultiValueMap getMultiValueMap(Object obj) {
		return webClientUtil.toQueryParams(obj);
	}
}
