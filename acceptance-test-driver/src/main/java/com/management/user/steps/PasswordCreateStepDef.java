package com.management.user.steps;

import com.management.user.dto.PasswordTokenDto;
import com.management.user.dto.UserDto;
import com.management.user.fakes.PasswordTokenInMemory;
import com.management.user.fakes.UserInMemory;
import com.management.user.models.PasswordCreateInput;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Assert;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PasswordCreateStepDef {
    private  final UserInMemory userInMemory;
    private final PasswordTokenInMemory passwordTokenInMemory;

    public PasswordCreateStepDef(UserInMemory userInMemory, PasswordTokenInMemory passwordTokenInMemory) {
        this.userInMemory = userInMemory;
        this.passwordTokenInMemory = passwordTokenInMemory;
    }
    public PasswordCreateStepDef() {
        this.userInMemory = new UserInMemory();  // or some default value
        this.passwordTokenInMemory = new PasswordTokenInMemory();
    }

    @DataTableType
    public UserDto userDtoTransformer(Map<String, String> row){
        return new UserDto(
                row.get("Employee Id"),
                row.get("First Name"),
                row.getOrDefault("Last Name", null),
                row.get("E-mail"),
                row.get("User Role"),
                row.get("Phone Number"),
                LocalDate.parse(row.getOrDefault("Hire Date", null)),
                LocalDate.parse(row.getOrDefault("Birth Date", null))
        );
    }

    private PasswordTokenDto passwordTokenTransformer(Map<String, String> row) {
        return new PasswordTokenDto(
                row.get("UserName"),
                row.get("Password"),
                LocalDateTime.parse(row.get("Expiry Date"))
        );
    }
    @Given("User has given the email and phone number as follows.")
    public void userHasGivenTheEmailAndPhoneNumberAsFollows(PasswordCreateInput passwordCreateInput){

    }

    @Then("Create a password for user")
    public void createAPasswordForUser(DataTable dataTable) {
        List<PasswordTokenDto> passwordTokenDtos = dataTable.asMaps(String.class, String.class).stream()
                .map(this::passwordTokenTransformer).collect(Collectors.toList());
        Assert.assertNotNull(passwordTokenInMemory.fetchPasswordToken(passwordTokenDtos.get(0).getUserName()));
    }


    @Given("Below is the user with the email {string}")
    public void belowIsTheUserWithTheEmail(String email, DataTable dataTable) {
        List<UserDto> users = dataTable.asMaps(String.class, String.class).stream()
                        .map(this::userDtoTransformer).collect(Collectors.toList());
        users.forEach(user ->
                userInMemory.put(email, user));
    }

    @When("User has given the email {string} and phone number {string} as follows.")
    public void userHasGivenTheEmailAndPhoneNumberAsFollows(String email, String phoneNumber) {
        UserDto userDto = userInMemory.fetchUserByEmail(email);
        if(Objects.nonNull(userDto) && Objects.equals(userDto.getPhone_number(), phoneNumber) && Objects.equals(userDto.getEmail(), email)){
            PasswordTokenDto passwordTokenDto = passwordTokenInMemory.createPassword();
            passwordTokenInMemory.savePasswordTokens(passwordTokenDto);
        }
    }

    @Then("Do not Create a password for user")
    public void doNotCreateAPasswordForUser(DataTable dataTable) {
        List<PasswordTokenDto> passwordTokenDtos = dataTable.asMaps(String.class, String.class).stream()
                .map(this::passwordTokenTransformer).collect(Collectors.toList());
        Assert.assertEquals(passwordTokenDtos.size(), 0);
    }
}
