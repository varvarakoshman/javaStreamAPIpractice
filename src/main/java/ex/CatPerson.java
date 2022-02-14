package ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CatPerson {
    private Person person;
    private List<Cat> cats;
}
