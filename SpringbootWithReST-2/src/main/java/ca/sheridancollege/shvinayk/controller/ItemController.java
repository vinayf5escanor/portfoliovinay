package ca.sheridancollege.shvinayk.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.shvinayk.bean.Item;
import ca.sheridancollege.shvinayk.database.DatabaseAccess;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	DatabaseAccess da;

// this is GET - retrieve the entire collection
	@GetMapping
	public List<Item> getStudentCollection() {
//		if (da.findAll().size() == 0) {
//			for (int i = 1; i <= 5; i++) {
//				da.save(new Item("Item " + i));
//			}
//		}
		return da.findAll();
	}

// this is GET - retrieve an individual entry from the collection
	@GetMapping("/{id}")
	public Item getIndividualItem(@PathVariable Long id) {
		return da.findById(id).get(0);
	}

// this is POST - create a new entry in the collection then
// return its id
	@PostMapping(consumes = "application/json")
	public Long postStudent(@RequestBody Item Item) {
		return da.save(Item);
	}

// this is PUT - Replace the entire collection
	@PutMapping(consumes = "application/json")
	public String putStudentCollection(@RequestBody List<Item> ItemList) {
		da.deleteAll();
		da.saveAll(ItemList);
		return "Total Records: " + da.count();
	}

// this is PUT - Replace an individual entry in the collection
	@PutMapping(path = "/{id}", consumes = "application/json")
	public String putStudent(@PathVariable Long id, @RequestBody Item Item) {
		return da.updateItem(id, Item);

	}

// this is DELETE - Replace the entire collection
	@DeleteMapping
	public String deleteStudentCollection() {
		da.deleteAll();
		return "Recoreds removed. Total Records: " + da.count();
	}

// this is DELETE - Remove an individual entry from the collection
	@DeleteMapping("/{id}")
	public String deleteItem(@PathVariable Long id) {
		da.deleteItem(id);
		return "Item removed.";
	}
}

