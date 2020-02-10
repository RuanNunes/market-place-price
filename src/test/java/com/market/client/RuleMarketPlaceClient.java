package com.market.client;

import com.market.contract.RuleMarketPlaceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "rule-market-place-price", url = "${market.api.uri}")
public interface RuleMarketPlaceClient extends RuleMarketPlaceApi {

}
