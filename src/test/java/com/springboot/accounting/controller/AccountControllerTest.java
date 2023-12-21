package com.springboot.accounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.accounting.dto.AccountDtoConverter;
import com.springboot.accounting.dto.CreateAccountRequest;
import com.springboot.accounting.model.Customer;
import com.springboot.accounting.repository.AccountRepository;
import com.springboot.accounting.repository.CustomerRepository;
import com.springboot.accounting.service.AccountService;
import com.springboot.accounting.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.UUID;
import java.util.function.Supplier;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class AccountControllerTest  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Clock clock;

    @MockBean
    private Supplier<UUID> uuidSupplier;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountDtoConverter accountDtoConverter;

    @Autowired
    private CustomerRepository customerRepository;

    private AccountService accountService = new AccountService(accountRepository, customerService, accountDtoConverter, clock);
    private ObjectMapper mapper = new ObjectMapper();

    private static final UUID uuid = UUID.randomUUID();

    @BeforeEach
    public void setup(){
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }

//    @Test
//    public void testCreateAccount_WhenCustomerIdExists_shouldCreateAccountAndReturnAccountDto() throws Exception{
//        Customer customer = customerRepository.save(new Customer("Mahmut","Emir"));
//
//        CreateAccountRequest createAccountRequest = new CreateAccountRequest(customer.getId(), new BigDecimal(100));
//
//        this.mockMvc.perform(post("/v1/account")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(createAccountRequest)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.balance", is(100)))
//                .andExpect(jsonPath("$.customer.id", is(customer.getId())))
//                .andExpect(jsonPath("$.customer.name", is(customer.getName())))
//                .andExpect(jsonPath("$.customer.surname", is(customer.getSurname())))
//                .andExpect(jsonPath("$.transactions", hasSize(1)));
//
//
//    }



}