package com.example.backend.controller;

import com.example.backend.model.Form;
import com.example.backend.repository.FormRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class FormController {
    private final FormRepository formRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('CREATOR', 'FILLER')")
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CREATOR', 'FILLER')")
    public Form getFormById(@PathVariable("id") Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Form not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('CREATOR')")
    public Form createForm(@RequestBody @Valid Form form) {
        return formRepository.save(form);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CREATOR')")
    public Form updateForm(@PathVariable("id") Long id, @RequestBody @Valid Form updatedForm) {
        return formRepository.findById(id)
                .map(form -> {
                    form.setName(updatedForm.getName());
                    form.setDescription(updatedForm.getDescription());
                    form.setFields(updatedForm.getFields());
                    return formRepository.save(form);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Form not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('CREATOR')")
    public void deleteForm(@PathVariable("id") Long id) {
        if (!formRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Form not found");
        }
        formRepository.deleteById(id);
    }
}
