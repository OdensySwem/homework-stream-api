package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Nikita", "Ivanka", "Leonid", "Ivan", "Julia", "Dejan", "Vladimir", "Polina", "Evdokia", "Luka");
        List<String> families = Arrays.asList("Ivanovich", "Modrich", "Kristich", "Dida", "Brozovich", "Petrenkovich", "Koloshic", "Frosuc", "Rodijonovich", "Pulishich");
        Collection<Person> serbianDataNamesCollection = new ArrayList<>();
        for(int i = 0; i < 500_000; i++){
            serbianDataNamesCollection.add(new Person(
                            names.get(new Random().nextInt(names.size())),
                            families.get(new Random().nextInt(families.size())),
                            new Random().nextInt(100),
                            Sex.values()[new Random().nextInt(Sex.values().length)],
                            Education.values()[new Random().nextInt(Education.values().length)]
                    )
            );
        }

        long countOfMinor = serbianDataNamesCollection.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println(countOfMinor);

        List<String> namesOfConscripts = serbianDataNamesCollection.stream()
                .filter(age -> age.getAge() >= 18 && age.getAge() < 27)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        serbianDataNamesCollection = serbianDataNamesCollection.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(age -> (age.getAge() >= 18 && age.getAge() < 60) || (age.getAge() >= 18 && age.getAge() < 65))
                .filter(sex -> (sex.getSex() == Sex.WOMAN) || (sex.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily).thenComparing(Person::getFamily))
                .collect(Collectors.toList());

        for (Person person: serbianDataNamesCollection){
            System.out.println(person.getName() + " " + person.getFamily() + " " + person.getAge() + "\n");
        }
    }
}