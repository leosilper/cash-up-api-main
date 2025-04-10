package br.com.fiap.cash_up_api.controller;
 
// import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.ExampleMatcher;
// import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import br.com.fiap.cash_up_api.model.Transaction;
import br.com.fiap.cash_up_api.repository.TransactionRepository;
import br.com.fiap.cash_up_api.specification.TransactionSpecification;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {
 
    public record TransactionFilter(String description, LocalDate startDate,  LocalDate endDate){}
 
    @Autowired
    private TransactionRepository repository;
 
    @GetMapping
    public Page<Transaction> index(TransactionFilter filter, @PageableDefault(size = 10, sort = "date", direction = Direction.DESC)  Pageable pageable){
        var spacification = TransactionSpecification.withFilters(filter);
        return repository.findAll(spacification, pageable);
       
    }
   
}
 
// log.info("Buscando transações com descrição {} e data {}", filter.description(), filter.date());
 
        // var probe = Transaction.builder()
        //                 .description(filter.description())
        //                 .date(filter.date())
        //                 .amount(filter.amount())
        //                 .build();
 
        // var matcher = ExampleMatcher
        //                     .matchingAll()
        //                     .withIgnoreCase()
        //                     .withStringMatcher(StringMatcher.CONTAINING);
       
        // var example = Example.of(probe, matcher)