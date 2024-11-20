package top.jonakls.processapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.jonakls.processapi.entity.ProcessEntity;
import top.jonakls.processapi.repository.ProcessRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public ResponseEntity<?> addProcess(final ProcessEntity product) {
        final Map<String, Object> response = new HashMap<>();

        final ProcessEntity process = this.processRepository.findByRadix(product.getRadix());

        if (process != null) {
            response.put("error", true);
            response.put("message", "Process already exists");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("error", false);
        response.put("message", "Process added successfully");
        response.put("data", this.processRepository.save(product));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getProcess(final int id) {
        final Map<String, Object> response = new HashMap<>();

        final Optional<ProcessEntity> processOptional = this.processRepository.findById(id);

        if (processOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "Process not found");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("error", false);
        response.put("message", "Process found successfully");
        response.put("data", processOptional.get());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getAllProcess() {
        final Map<String, Object> response = new HashMap<>();

        response.put("error", false);
        response.put("message", "Process found successfully");
        response.put("data", this.processRepository.findAll());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> deleteProcess(final int id) {
        final Map<String, Object> response = new HashMap<>();

        final Optional<ProcessEntity> productOptional = this.processRepository.findById(id);

        if (productOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "Process not found");
            return ResponseEntity.badRequest().body(response);
        }

        this.processRepository.deleteById(id);

        response.put("error", false);
        response.put("message", "Process deleted successfully");
        return ResponseEntity.ok(response);
    }
}
