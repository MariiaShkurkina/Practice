package com.example.task5;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.task5.Controllers.VisitorController;
import com.example.task5.DTO.VisitorRequestDTO;
import com.example.task5.DTO.VisitorResponseDTO;
import com.example.task5.Service.VisitorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;


@WebMvcTest(VisitorController.class)
class VisitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorService visitorService;

    @Test
    void testFindAllVisitors() throws Exception {
        List<VisitorResponseDTO> visitors = List.of(
                new VisitorResponseDTO(1L, "Иван", 30, "Мужской"),
                new VisitorResponseDTO(2L, "Мария", 25, "Женский")
        );

        Mockito.when(visitorService.findAll()).thenReturn(visitors);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/visitors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Иван"));
    }


    @Test
    void testCreateVisitor() throws Exception {
        VisitorRequestDTO request = new VisitorRequestDTO("Иван", 30, "Мужской");
        VisitorResponseDTO response = new VisitorResponseDTO(1L, "Иван", 30, "Мужской");

        Mockito.when(visitorService.save(any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "name": "Иван",
                      "age": 30,
                      "gender": "Мужской"
                    }
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Иван"));
    }

    @Test
    void testUpdateVisitor() throws Exception {
        VisitorRequestDTO request = new VisitorRequestDTO("Пётр", 35, "Мужской");
        VisitorResponseDTO response = new VisitorResponseDTO(1L, "Пётр", 35, "Мужской");

        Mockito.when(visitorService.update(eq(1L), any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/visitors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "name": "Пётр",
                      "age": 35,
                      "gender": "Мужской"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Пётр"))
                .andExpect(jsonPath("$.age").value(35))
                .andExpect(jsonPath("$.gender").value("Мужской"));
    }

    @Test
    void testDeleteVisitor() throws Exception {
        Mockito.doNothing().when(visitorService).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/visitors/1"))
                .andExpect(status().isNoContent());
    }
}

