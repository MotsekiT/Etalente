package com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.waste;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.enviro.assessment.grad001.motsekitshabalala.inmemorydatabase.H2.jpa.WasteManagementRepository;

import jakarta.validation.Valid;

@RestController
public class WasteManagementResource {


	private WasteManagementRepository repository;

	public WasteManagementResource( WasteManagementRepository repository) {
		// Initialize the WasteJpaResource with the provided WasteManagementRepository
		this.repository = repository;
	}//end


	//Retrieves all waste items from the repository.
	@GetMapping("/retrieve/wastes")
	public List<WasteManagement> retrieveAllWaste(){
		//Using the repository's findAll() method to retrieve all waste items
		return repository.findAll();

	}//end


	//Retrieves a specific waste item by its ID from the repository.
	@GetMapping("/get/wastes/{id}")
	public EntityModel<WasteManagement> retrieveWaste(@PathVariable int id){
		// Retrieve the waste item with the specified ID from the repository
		Optional<WasteManagement> wasteManagement = repository.findById(id);

		// If the waste item is not found, throw a WasteNotFoundException
		if(wasteManagement.isEmpty())
			throw new WasteNotFoundException("id:" + id);

		// Create an EntityModel for the retrieved waste item
		EntityModel<WasteManagement> entityModel = EntityModel.of(wasteManagement.get());
		// Create a link to retrieve all waste items
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllWaste());
		// Add the link to the EntityModel with the relation "all-waste"
		entityModel.add(link.withRel("all-waste"));
		// Return the EntityModel containing the retrieved waste item
		return entityModel;

	}//end


	
	//Creates a new waste item and saves it to the repository.
	@PostMapping("/save/wastes")
	public ResponseEntity<WasteManagement> createWaste(@Valid @RequestBody WasteManagement wasteManagement) {
		// Save the waste item to the repository and retrieve the saved instance
		WasteManagement savedWaste = repository.save(wasteManagement);
		// Build the URI for the newly created resource
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedWaste.getId())
				.toUri();
		// Return a ResponseEntity indicating success (HTTP 201 Created) along with the URI of the newly created resource
		return ResponseEntity.created(location).build();
	}//end

	
	//Deletes a waste item from the repository by its ID
	@DeleteMapping("/delete/wastes/{id}")
	public void deteleWaste(@PathVariable int id){
		// Delete the waste item from the repository by its ID
		repository.deleteById(id);

	}//end


	//Updates a waste item in the repository by its ID.
	@PutMapping("/update/wastes/{id}")
    public ResponseEntity<WasteManagement> updateWasteById(@PathVariable int id, @RequestBody WasteManagement wasteManagement) {
		// Check if the waste item with the specified ID exists in the repository
		Optional<WasteManagement> wasteOptional = repository.findById(id);

		// If the waste item is not found, return a ResponseEntity with HTTP status code 404 (Not Found)
		if (wasteOptional.isEmpty())
			return ResponseEntity.notFound().build();
		// Set the ID of the updated waste item to match the specified ID
		wasteManagement.setId(id);
		// Save the updated waste item to the repository
		repository.save(wasteManagement);
		// Return a ResponseEntity indicating success with HTTP status code 204 (No Content)
		return ResponseEntity.noContent().build();

    }//end



}//end
