package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
//@ComponentScan(basePackages = "com.amigoscode") // Here we tell Spring to look the components from com.amigoscode package, and not from src.
//@EnableAutoConfiguration
@RestController // We must annotate our class with this @RestController in order for the endpoints to work.
@RequestMapping("api/v1/customers") // You can define at root level the request mapping, such that al of MAin clas´s methods will have "api1/v1/customers" on their urls
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

//    @GetMapping("/") // In order to expose our method to receive GET requests, we must use the @GetMapping annotation.
//    public String greet() {
//        return "Hello! Peaches Peaches Peaches Peaches Peaches!";
//    }

//    @GetMapping("/greet") // In order to expose our method to receive GET requests, we must use the @GetMapping annotation.
//    public String greet2() {
//        return "Hello! Peache understaaaaand!";
//    }

    // So because we put "api/v1/customers" on the RequestMapping at root level, whenm they visit this link
    // we are gonna get this List, because we didn´t sepcify anything for it or URL, like when using @GetMappíng.
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}

    // We want to capture the request, which will be a JSON object, for usch we annotate our parameter with @RequestBody.
    // Because of that our request will be mapped into this parameter of NewCustomerRequest class.
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer); // this will add a new Customer to our customer table.
    }

    // Because we need to know which customer to change, we must pass the customer Id in the request,
    // therefore we will put "{customerId}" as a parameter to @PutMapping.
    // We also need to map this CustomerId from the request to a parameter as @PathVariable("customerId")
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer updatedCustomer = customerRepository.findById(id).orElseThrow();
        updatedCustomer.setName(request.name);
        updatedCustomer.setEmail(request.email);
        updatedCustomer.setAge(request.age);
        customerRepository.save(updatedCustomer);
    }

    // We know that to delete a resource, we would delete it from its number right?
    // Considering our request mapping with "api/v1/customers", we would , for instance, delete
    // api/v1/customers/1.
    // In order to recognize this ID, we have to pass it (a String with curly brackets) as parameter to @DeleteMapping.
    // In our case we called it "{customerId}".
    //
    // But we must also map this "customerId" to our parameter, so we do so as a path variable by
    // putting @PathVariable("customerId") before our parameter. Here I didn´t add the curly braces!
    @DeleteMapping("{customerId}") // @DeleteMapping allows us to delete a resource from our database by calling this method
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        // Wont even check if customer exists or not, we just want to be straight forward here
        customerRepository.deleteById(id);
    }
    @GetMapping("/greet")
    public GreetResponse greet() {
        GreetResponse response = new GreetResponse(
                "Hello",
                List.of("Java", "Python", "JavaScript"),
                new Person("Kenny Troll",28,30_000)
        );
        return response; // this is the response we wanna send back to the client;
    }

    record Person(String name, int age, double savings) {}
    record GreetResponse(String greet,
                         List<String> favProgrammingLanguage,
                         Person person){}
}
