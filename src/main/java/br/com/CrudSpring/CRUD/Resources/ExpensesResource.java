package br.com.CrudSpring.CRUD.Resources;

import br.com.CrudSpring.CRUD.Model.Expenses;
import br.com.CrudSpring.CRUD.Repositories.ExpensesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api
@RestController
@RequestMapping("/expenses")
public class ExpensesResource {

    private ExpensesRepository expensesRepository;

    public ExpensesResource(ExpensesRepository expensesRepository) {
        super();
        this.expensesRepository = expensesRepository;
    }

    @ApiOperation("Armazena uma despesa")
    @PostMapping
    public ResponseEntity<Expenses> save(@RequestBody Expenses expenses){

        expensesRepository.save(expenses);

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @ApiOperation("Busca todas despesas")
    @GetMapping
    public ResponseEntity<List<Expenses>> getAll(){
        List<Expenses> list = expensesRepository.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Consulta uma despesa pelo seu id")
    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Expenses>> getbyId(@PathVariable Integer id){

        try{
            Optional<Expenses> expenses = expensesRepository.findById(id);

            return new ResponseEntity<>(expenses, HttpStatus.OK);

        } catch (NoSuchElementException er) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Remove uma Despesa pelo seu id")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Expenses> removeById(@PathVariable Integer id){
        try{
            expensesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException er) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Atualiza os dados de despesa")
    @PutMapping(path="/{id}")
    public ResponseEntity<Expenses> update(@PathVariable Integer id, @RequestBody Expenses newExpenses){
        try {

            return expensesRepository.findById(id)
                    .map(expenses-> {
                        expenses.setDate(newExpenses.getDate());
                        expenses.setKm(newExpenses.getKm());
                        expenses.setDescription(newExpenses.getDescription());
                        expenses.setLiters(newExpenses.getLiters());
                        expenses.setValue(newExpenses.getValue());
                        Expenses expenseUpdated = expensesRepository.save(newExpenses);
                        return ResponseEntity.ok().body(expenseUpdated);
                    }).orElse(ResponseEntity.notFound().build());


        } catch (NoSuchElementException er) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
