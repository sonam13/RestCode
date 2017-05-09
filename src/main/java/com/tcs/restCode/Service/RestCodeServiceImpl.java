package com.tcs.restCode.Service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.restCode.Request.Request;
@Service
public class RestCodeServiceImpl implements RestCodeService{
	@Inject
	private RestTemplate restTemplate;
	@Value("${restCodeGenerationUrl}")
	private String restCodeGenerationUrl;
	@Value("${restClientUrl}")
	private String restClientUrl;
	@Value("${restResponseCustomizationUrl}")
	private String restResponseCustomizationUrl;
	@Override
	public Object getResponse(Request request) {
		Object response= restTemplate.postForObject(restClientUrl,request, Object.class );
		request.setServiceResponse(response);
		  restTemplate.postForObject(restCodeGenerationUrl,request, String.class );
		  restTemplate.postForObject(restResponseCustomizationUrl,request, String.class );
		return response;
	}

}
