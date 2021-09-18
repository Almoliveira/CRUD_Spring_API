package br.com.CrudSpring.CRUD.Repositories;

import br.com.CrudSpring.CRUD.Model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {


}
