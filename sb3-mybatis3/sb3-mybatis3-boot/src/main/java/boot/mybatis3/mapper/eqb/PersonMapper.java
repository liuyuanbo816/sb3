package boot.mybatis3.mapper.eqb;

import boot.mybatis3.pojo.Person;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonMapper {

    LocalDateTime getDBNow();

    Person getPersonById(Long id);

    List<Person> getPersons();
}