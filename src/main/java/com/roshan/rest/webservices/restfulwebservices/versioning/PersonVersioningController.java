package com.roshan.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	// URI Versioning

	@GetMapping("/v1/person")
	public PersonV1 urlPersonV1() {
		return new PersonV1("Roshan Bhavsar");
	}

	@GetMapping("/v2/person")
	public PersonV2 urlPersonV2() {
		return new PersonV2(new Name("Roshan", "Bhavsar"));
	}

	// Params Versioning
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramPersonV1() {
		return new PersonV1("Roshan Bhavsar");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramPersonV2() {
		return new PersonV2(new Name("Roshan", "Bhavsar"));
	}

	// Header Versioning

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerPersonV1() {
		return new PersonV1("Roshan Bhavsar");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerPersonV2() {
		return new PersonV2(new Name("Roshan", "Bhavsar"));

	}

	// Produce Versioning OR Accept Versioning OR Media Type Versioning

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesPersonV1() {
		return new PersonV1("Roshan Bhavsar");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesPersonV2() {
		return new PersonV2(new Name("Roshan", "Bhavsar"));
		

	}

}
