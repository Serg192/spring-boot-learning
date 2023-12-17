package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    undefined("Невизначено"),
    male("Чоловік"),
    female("Жінка");

    private String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public static Gender getGenderByName(String name) {
        Optional<Gender> genderOptional = Arrays.stream(Gender.values())
                .filter(gender -> gender.getDisplayName().equalsIgnoreCase(name))
                .findFirst();

        return genderOptional.orElse(Gender.undefined);
    }
}