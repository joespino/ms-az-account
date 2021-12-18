package aforo255.com.msazaccount.repository;

import org.springframework.data.repository.CrudRepository;

import aforo255.com.msazaccount.model.entity.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity,Integer> {

}
