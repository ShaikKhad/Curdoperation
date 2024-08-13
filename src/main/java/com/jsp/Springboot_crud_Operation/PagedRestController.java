package com.jsp.Springboot_crud_Operation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagedRestController<Students> {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String page() {
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUsers(@RequestBody User user) {
        userRepo.save(user);
        return "Saved...";
    }
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return "Updated...";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(deleteUser);
        return "Deleted user with id " + id;
    }

    @GetMapping("/users/page/size")
    public Page<User> getUsersWithPagination(
        @RequestParam int page,
        @RequestParam int size) {
        Pageable paging = PageRequest.of(page,size);
        return userRepo.findAll(paging);
    }

    @GetMapping("/sorting") 
	public Iterable<User>getAllByCols (@RequestParam String field1) 
	{ 
		return userRepo.findAll(Sort.by(Direction.ASC, field1)); 
	}
}
