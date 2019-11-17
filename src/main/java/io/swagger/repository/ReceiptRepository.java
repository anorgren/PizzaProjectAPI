package io.swagger.repository;

import io.swagger.model.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReceiptRepository extends MongoRepository<Receipt, String> {

  Receipt findByReceiptId(String receiptId);
}
