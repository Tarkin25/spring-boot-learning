package ch.noseryoung.learning;

import ch.noseryoung.learning.domain.employee.Employee;
import ch.noseryoung.learning.domain.employee.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        Employee alex = new Employee();
        alex.setName("alex");

        entityManager.persistAndFlush(alex);

        Employee found = employeeRepository.findByName(alex.getName());

        assertThat(found.getName()).isEqualTo(alex.getName());
    }

}
