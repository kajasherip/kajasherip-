package com.example.demo.controller;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataServices;

@Controller
public class HomeController {

	CoronaVirusDataServices crnService;
	int countryid;
	int id = 0;

	@Autowired
	public void setCrnService(CoronaVirusDataServices crnService) {
		this.crnService = crnService;
	}

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/archived_data/archived_daily_case_updates/02-12-2020_1020.csv";

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStates> allstates = crnService.getAllstates();
		int totalDeathsReported = allstates.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates", allstates);
		model.addAttribute("totalDeathsReported", totalDeathsReported);
		return "home";
	}

	@RequestMapping(path = "/chartData", produces = "application/json")
	@ResponseBody
	public List<LocationStates> chartData(Model model) {

		System.out.println("View Data Chart...");
		List<LocationStates> allstates = crnService.getAllstates();
		int totalDeathsReported = allstates.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates", allstates);
		model.addAttribute("totalDeathsReported", totalDeathsReported);
		for (int i = 1; i <= allstates.size(); i++) {
			countryid = i;
		}
		System.out.println(countryid);

		return allstates;
	}

	@GetMapping("/barChart")
	public String barChart() {
		return "chart";
	}

}
