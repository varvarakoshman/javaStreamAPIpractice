package ex;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class Solutions {

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
        Integer minAge = getCatPersons().stream()
                .filter(catPerson -> !catPerson.getCats().isEmpty())
                .map(catPerson -> catPerson.getPerson().getAge())
                .min(Comparator.naturalOrder())
                .get();
        assertThat(minAge, is(50));
    }

    // какой работник - самый молодой кошатник?
    @Test
    void getYoungestCatPerson() {
        Person youngestCatPerson = getCatPersons().stream()
                .filter(catPerson -> !catPerson.getCats().isEmpty())
                .map(CatPerson::getPerson)
                .min(Comparator.comparing(Person::getAge))
                .get();
        assertThat(youngestCatPerson.getName(), is("Бэкендер"));
    }

    // все породы кошек всех работников
    @Test
    void getAllCatBreeds() {
        Set<String> breedsSet = getCatPersons().stream()
                .flatMap(catP -> catP.getCats().stream())
                .map(Cat::getBreed)
                .collect(Collectors.toSet());
        assertThat(breedsSet, containsInAnyOrder("персидская", "сфинкс", "бенгальская", "сиамская"));
        assertThat(breedsSet.size(), is(4));
    }

    // количество кошек каждой породы - мапа (порода, количество)
    @Test
    void getAllCatBreedsCountMap() {
        Map<String, Long> catBreedsCount = getCatPersons().stream()
                .map(CatPerson::getCats)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Cat::getBreed, Collectors.counting()));
        assertThat(catBreedsCount, hasEntry("персидская", 2L));
        assertThat(catBreedsCount, hasEntry("сфинкс", 3L));
        assertThat(catBreedsCount, hasEntry("бенгальская", 2L));
        assertThat(catBreedsCount, hasEntry("сиамская", 1L));
    }

    // число кошек/имена кошек, моложе 5
    @Test
    void getCatsCountYoungerThan5() {
        long catsCount = getCatPersons().stream()
                .map(CatPerson::getCats)
                .flatMap(Collection::stream)
                .filter(cat -> cat.getAge() < 5)
                .count();
        assertThat(catsCount, is(5L));
    }

    // работник с максимальным количеством общего числа лет всем его кошкам
    @Test
    void sortPersonsByCatNum() {
        CatPerson catPerson = getCatPersons().stream()
                .max(Comparator.comparing(catPers -> catPers.getCats().stream()
                        .mapToInt(Cat::getAge)
                        .sum())).get();
        assertThat(catPerson.getPerson().getName(), is("Тестировщик"));
    }

    // список работников, у которых есть сфинкс
    @Test
    void getPersonNamesWithSphinx() {
        List<String> personsNames = getCatPersons().stream()
                .filter(catPerson -> catPerson.getCats().stream()
                        .map(Cat::getBreed)
                        .anyMatch(breed -> Objects.equals("сфинкс", breed)))
                .map(catPerson -> catPerson.getPerson().getName())
                .collect(Collectors.toList());
        assertThat(personsNames, containsInAnyOrder("Бэкендер", "Тестировщик"));
        assertThat(personsNames.size(), is(2));
    }
}