package people;

import java.util.UUID;

public class Person {

    private String id;
    private String name;

    Person(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
