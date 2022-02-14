package ex;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class Exercises {

    private List<CatPerson> getCatPersons() {
        List<CatPerson> catPersons = new ArrayList<>();
        catPersons.add(new CatPerson(new Person("Менеджер", 40), Collections.emptyList())); // !!!
        catPersons.add(new CatPerson(new Person("Бэкендер", 50), Arrays.asList(
                new Cat("Искорка", "персидская", 3),
                new Cat("Редиска", "сфинкс", 4))));
        catPersons.add(new CatPerson(new Person("Фронтендер", 60), Collections.singletonList(
                new Cat("Ириска", "бенгальская", 2))));
        catPersons.add(new CatPerson(new Person("Аналитик", 70), Arrays.asList(
                new Cat("Мура", "сиамская", 3),
                new Cat("Царапка", "бенгальская", 5))));
        catPersons.add(new CatPerson(new Person("Тестировщик", 80), Arrays.asList(
                new Cat("Кася", "персидская", 3),
                new Cat("Живоглот", "сфинкс", 5),
                new Cat("Миссис Норрис", "сфинкс", 10))));
        return catPersons;
    }

    // какой возраст у самого молодого кошатника?
    @Test
    void getYoungestCatPersonAge() {
        Integer minAge = null;
        assertThat(minAge, is(50));
    }

    // какой работник - самый молодой кошатник?
    @Test
    void getYoungestCatPerson() {
        Person youngestCatPerson = null;
        assertThat(youngestCatPerson.getName(), is("Бэкендер"));
    }

    // все породы кошек всех работников
    @Test
    void getAllCatBreeds() {
        Set<String> breedsSet = null;
        assertThat(breedsSet, containsInAnyOrder("персидская", "сфинкс", "бенгальская", "сиамская"));
        assertThat(breedsSet.size(), is(4));
    }

    // количество кошек каждой породы - мапа (порода, количество)
    @Test
    void getAllCatBreedsCountMap() {
        Map<String, Long> catBreedsCount = null;
        assertThat(catBreedsCount, hasEntry("персидская", 2L));
        assertThat(catBreedsCount, hasEntry("сфинкс", 3L));
        assertThat(catBreedsCount, hasEntry("бенгальская", 2L));
        assertThat(catBreedsCount, hasEntry("сиамская", 1L));
    }

    // число кошек/имена кошек, моложе 5
    @Test
    void getCatsCountYoungerThan5() {
        Long catsCount = null;
        assertThat(catsCount, is(5L));
    }

    // работник с максимальным количеством общего числа лет всем его кошкам
    @Test
    void sortPersonsByCatNum() {
        CatPerson catPerson = null;
        assertThat(catPerson.getPerson().getName(), is("Тестировщик"));
    }

    // список работников, у которых есть сфинкс
    @Test
    void getPersonNamesWithSphinx() {
        List<String> personsNames = null;
        assertThat(personsNames, containsInAnyOrder("Бэкендер", "Тестировщик"));
        assertThat(personsNames.size(), is(2));
    }
}