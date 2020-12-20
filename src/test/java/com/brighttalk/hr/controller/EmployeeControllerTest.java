package com.brighttalk.hr.controller;

import com.brighttalk.hr.entity.Employee;
import com.brighttalk.hr.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
@WithMockUser
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    List<Employee> employeeList = null;

    Employee employee = null;

    @BeforeEach
    void setUp() {
        employee = new Employee(new BigInteger("1"), "firstName", "lastName", "firtName@email.com", "000-000-0000");
        employeeList = Collections.singletonList(employee);
    }

    @Test
    void getAllEmployeeDetails() throws Exception {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/all");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":1,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"firtName@email.com\",\"phone\":\"000-000-0000\"}]";
        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    void getEmployeeDetailById() throws Exception {
        Mockito.when(employeeService.getEmployeeById(new BigInteger("1"))).thenReturn(Optional.ofNullable(employee));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/1");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":1,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"firtName@email.com\",\"phone\":\"000-000-0000\"}";
        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    void deleteEmployeeById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/1");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void createNewEmployee() throws Exception {
        String employeeAsJson = "{\"id\":1,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"firtName@email.com\",\"phone\":\"000-000-0000\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee/create")
                                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                .content(employeeAsJson);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    void updateEmployeeId() throws Exception {
        String employeeAsJson = "{\"id\":1,\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"email\":\"firtName@email.com\",\"phone\":\"000-000-0000\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employee/1")
                                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                              .content(employeeAsJson);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void deleteAllEmployee() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/all");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(202, mvcResult.getResponse().getStatus());
    }
}