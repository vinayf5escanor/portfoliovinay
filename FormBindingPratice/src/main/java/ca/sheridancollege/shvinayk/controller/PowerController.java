package ca.sheridancollege.shvinayk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.shvinayk.beans.Cast;
import ca.sheridancollege.shvinayk.beans.sds;

@Controller
public class PowerController {
	@Autowired
	private sds sev;

	@GetMapping("/")
	public String index(Model model, Cast cast) {
		model.addAttribute("sds", sev);
		return "index";

	}

	@PostMapping("/list")
	public String data(Model model, @ModelAttribute Cast cast) {
		sev.getList().add(cast);
		model.addAttribute("sds", sev);
		return "index";

	}
}
