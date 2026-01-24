package com.example.backend.controller;

import com.example.backend.model.Form;
import com.example.backend.repository.FormRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(roles = "CREATOR")
class FormControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FormRepository formRepository;

    @Test
    @DisplayName("Should create a form and return 201")
    void createForm() throws Exception {
        String formJson = "{" +
                "\"name\": \"Test Form\"," +
                "\"description\": \"A test form\"," +
                "\"fields\": []" +
                "}";
        mockMvc.perform(post("/api/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Test Form"));
    }

    @Test
    @DisplayName("Should return 400 for invalid form")
    void createForm_invalid() throws Exception {
        String formJson = "{" +
                "\"name\": \"\"," +
                "\"description\": \"\"," +
                "\"fields\": []" +
                "}";
        mockMvc.perform(post("/api/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    @Test
    @DisplayName("Should get all forms")
    void getAllForms() throws Exception {
        Form form = new Form();
        form.setName("Form1");
        form.setDescription("desc");
        formRepository.save(form);
        mockMvc.perform(get("/api/forms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Form1"));
    }

    @Test
    @DisplayName("Should return 404 for non-existent form")
    void getFormById_notFound() throws Exception {
        mockMvc.perform(get("/api/forms/9999"))
                .andExpect(status().isNotFound());
    }
}
