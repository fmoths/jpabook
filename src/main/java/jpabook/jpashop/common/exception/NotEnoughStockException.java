package jpabook.jpashop.common.exception;

public class NotEnoughStockException extends Exception {
    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
