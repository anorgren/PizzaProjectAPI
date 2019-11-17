package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.swagger.model.Receipt;

public interface ReceiptRepository extends MongoRepository<Receipt, String> {
  Receipt findByReceiptId(String receiptId);
}
