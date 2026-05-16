package learnSB.project.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import learnSB.project.Domain.Member;
import learnSB.project.Domain.StockLedger;
import learnSB.project.DTO.Request.AdjustStockRequest;
import learnSB.project.Service.StockService;
import learnSB.project.Service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final StockService stockService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Admin dashboard";
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return userService.getAllMembers();
    }

    /**
     * Điều chỉnh tồn kho thủ công (chỉ admin).
     * POST /api/admin/stock/adjust
     */
    @PostMapping("/stock/adjust")
    public ResponseEntity<StockLedger> adjustStock(
            @Valid @RequestBody AdjustStockRequest request,
            Principal principal) {
        StockLedger ledger = stockService.adjustStock(request, principal.getName());
        return ResponseEntity.ok(ledger);
    }
}
