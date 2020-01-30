package com.market.client;

import com.market.contract.CostumerApi;

//@FeignClient(name = "costumer", url = "${market.api.uri}")
public interface CostumerClient extends CostumerApi {

}
