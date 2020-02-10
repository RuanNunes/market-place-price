package com.market.client;

import com.market.contract.CostumerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "costumer", url = "${market.api.uri}")
public interface CostumerClient extends CostumerApi {

}
