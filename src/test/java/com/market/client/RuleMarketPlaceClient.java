package com.market.client;

import com.market.contract.CostumerApi;
import com.market.contract.RuleMarketPlaceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "rule-market-place-price", url = "${market.api.uri}")
public interface RuleMarketPlaceClient extends RuleMarketPlaceApi {

}
