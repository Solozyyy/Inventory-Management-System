package learnSB.project.Exception;

public class DuplicateLedgerEntryException extends RuntimeException {
    public DuplicateLedgerEntryException(String message) {
        super(message);
    }
}
