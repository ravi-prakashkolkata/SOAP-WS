package com.learning.soapws.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.learning.soapws.io.GetCountryRequest;
import com.learning.soapws.io.GetCountryResponse;
import com.learning.soapws.repository.CountryRepository;

@Endpoint
public class CountryApi {
	private static final String NAMESPACE_URI="http://spring.io/guides/gs-producing-web-service";
	
	private CountryRepository repo;
	
	@Autowired
	CountryApi(CountryRepository repo)
	{
		this.repo= repo;
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI,localPart="GetCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload  GetCountryRequest request) {
		GetCountryResponse res=new GetCountryResponse();
		res.setCountry(repo.findCountry(request.getName()));
		return res;
		}
	
	

}
