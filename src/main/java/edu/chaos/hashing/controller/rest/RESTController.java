package edu.chaos.hashing.controller.rest;

/*
@author   george
@project   hashing
@class RESTController
@version  1.0.0
@since 31.01.2021 - 17.31
*/

import edu.chaos.hashing.model.HashString;
import edu.chaos.hashing.model.RandomTest;
import edu.chaos.hashing.service.HashingService;
import edu.chaos.hashing.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/hash")
public class RESTController {

	@Autowired
	TestService testService;


	@PostMapping("/string")
	public HashString hashString(@RequestBody String string) {
		return getHashResults(string);
	}

	@PostMapping("/strings")
	public List<HashString> hashStrings(@RequestBody List<String> strings) {

		return strings.stream()
					  .map(this::getHashResults)
					  .collect(Collectors.toList());
	}

	@GetMapping("/random")
	public RandomTest getRandomTest(@RequestParam int num) {
		LocalTime start = LocalTime.now();

		RandomTest test = testService.getTestResult(num);

		Long duration = ChronoUnit.MILLIS.between(LocalTime.now(), start);
        test.setTime(duration);
		return test;
	}

	private HashString getHashResults(@RequestBody String string) {
		LocalTime start = LocalTime.now();
		String result = HashingService.getHashString(string);


		return new HashString(string, result,  ChronoUnit.MILLIS.between(LocalTime.now(), start));
			/*	HashString.builder()
						 .source(string)
						 .result(result)
						 .time(System.currentTimeMillis() - startTime)
						 .build();*/
	}
}
