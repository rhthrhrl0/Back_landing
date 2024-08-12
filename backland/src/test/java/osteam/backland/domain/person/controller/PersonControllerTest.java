package osteam.backland.domain.person.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.service.PersonSearchService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
@ActiveProfiles("default")
public class PersonControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonCreateService personCreateService;

    @MockBean
    private PersonSearchService personSearchService;

    @Test
    @DisplayName("사람이름이 너무 길어서 ExceptionResponse 반환")
    void longNamePersonTest() throws Exception {
        // given
        String longNamePerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        "teamaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "01012341234"
                ));

        // when, then
        mock.perform(MockMvcRequestBuilders.post("/person/create")
                        .content(longNamePerson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("이름은 1자 이상 20자 이하여야 합니다.")));
    }

    @Test
    @DisplayName("폰 번호 양식에 맞지 않아서 ExceptionResponse 반환 - 너무 긴 경우")
    void longPhonePersonTest() throws Exception {
        // given
        String longPhonePerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        "team",
                        "010-12341234-1111111111111111111111"
                ));

        // when, then
        mock.perform(MockMvcRequestBuilders.post("/person/create")
                        .content(longPhonePerson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("핸드폰 번호 양식에 맞지 않습니다. ex) 010-0000-0000")));
    }

    @Test
    @DisplayName("폰 번호 양식에 맞지 않아서 ExceptionResponse 반환 - 너무 짧은 경우")
    void shortPhonePersonTest() throws Exception {
        String shortPhonePerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        "team",
                        "010"
                ));

        // when, then
        mock.perform(MockMvcRequestBuilders.post("/person/create")
                        .content(shortPhonePerson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("핸드폰 번호 양식에 맞지 않습니다. ex) 010-0000-0000")));
    }

    @Test
    @DisplayName("이름과 폰 번호에 NULL 들어갈 경우 ExceptionResponse 반환")
    void nullPersonTest() throws Exception {
        String nullPerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        null,
                        null
                ));

        // when, then
        mock.perform(MockMvcRequestBuilders.post("/person/create")
                        .content(nullPerson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("폰 번호는 NULL 일 수 없습니다.")))
                .andExpect(jsonPath("$.message").value(containsString("이름은 NULL 일 수 없습니다.")));
    }

    @Test
    @DisplayName("사람 이름과 폰 번호에 Blank가 들어간 경우")
    void blankPersonTest() throws Exception {
        // given
        String blankPerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        "",
                        ""
                ));

        // when, then
        mock.perform(MockMvcRequestBuilders.post("/person/create")
                        .content(blankPerson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("이름은 1자 이상 20자 이하여야 합니다.")))
                .andExpect(jsonPath("$.message").value(containsString("핸드폰 번호 양식에 맞지 않습니다. ex) 010-0000-0000")));

    }

    @Test
    void nullInputTest() throws JsonProcessingException {
        String longPhonePerson = objectMapper
                .writeValueAsString(null);
    }


    @Test
    void changeNamePersonTest() throws JsonProcessingException {
        String originName = "sos";
        String phone = "01012341234";
        String changeName = "team";

        String successPerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        originName,
                        phone
                ));

        String changePerson = objectMapper
                .writeValueAsString(new PersonCreateRequest(
                        changeName,
                        phone
                ));
    }
}
