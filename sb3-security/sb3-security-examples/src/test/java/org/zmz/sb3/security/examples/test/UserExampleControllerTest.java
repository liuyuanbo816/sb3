package org.zmz.sb3.security.examples.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.zmz.sb3.security.examples.controller.UserExampleController;
import org.zmz.sb3.security.examples.filter.InvokeTimeFilter;
import org.zmz.sb3.security.examples.vo.request.UserPageRequest;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserExampleControllerTest {

    public static final Logger LOG = LoggerFactory.getLogger(UserExampleControllerTest.class);

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @BeforeEach
    void BeforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testUser() throws Exception {
        String response = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }

    @Test
    void testUsername() throws Exception {
        String response = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/name")
                                .param("name", "忽必烈")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }

    @Test
    void testUserPage() throws Exception {
        UserPageRequest request = new UserPageRequest();
        request.setName("哈喽我的");
        request.setAge(19);
        request.setXxx("其余信息");

        String response = this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/page")
                                .content(asJsonString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }

    @Test
    void testUserResponse() throws Exception {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(new UserExampleController());
        InvokeTimeFilter invokeTimeFilter = new InvokeTimeFilter();
        invokeTimeFilter.setRequestMappingHandlerMapping(requestMappingHandlerMapping);
        this.mockMvc = standaloneMockMvcBuilder.addFilters(invokeTimeFilter).build();
        String response = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/1001")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uid").value("1001"))
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }

    @Test
    void testUserResponses() throws Exception {
        String response = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/list")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(6))
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }

    @Test
    void testMapList() throws Exception {
        String response = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/mapList").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }

    @Test
    void testFileUpload() throws Exception {
        String response
                = this.mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST, "/file/upload")
                        .file(
                                new MockMultipartFile("file",
                                        "test.txt",
                                        MediaType.MULTIPART_FORM_DATA_VALUE,
                                        "HelloUpload".getBytes(StandardCharsets.UTF_8))
                        )
                ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        LOG.info("{}", response);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
