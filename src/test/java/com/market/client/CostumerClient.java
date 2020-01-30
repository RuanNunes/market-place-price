package com.market.client;

import com.market.contract.CostumerApi;
import com.market.contract.dto.CostumerDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "costumer", url = "${market.api.uri}")
public interface CostumerClient extends CostumerApi {

}
