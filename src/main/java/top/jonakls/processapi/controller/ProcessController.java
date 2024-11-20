package top.jonakls.processapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.jonakls.processapi.entity.ProcessEntity;
import top.jonakls.processapi.service.ProcessService;

@RestController
@RequestMapping("/api/v1/process")
public class ProcessController {

    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllProducts() {
        return this.processService.getAllProcess();
    }

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld() {
        return ResponseEntity.ok("Hello World, i'm a process API :)");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody final ProcessEntity processEntity) {
        return this.processService.addProcess(processEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable final int id) {
        return this.processService.getProcess(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable final int id) {
        return this.processService.deleteProcess(id);
    }
}
