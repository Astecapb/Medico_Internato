package br.com.internato.api.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.internato.api.dto.request.AlocacaoRequest;
import br.com.internato.api.dto.response.AlocacaoResponse;
import br.com.internato.domain.Alocacao;
import br.com.internato.service.AlocacaoService;



@RestController
@RequestMapping("/api/alocacoes")

public class AlocacaoController {

    private final AlocacaoService service;
    public AlocacaoController(AlocacaoService service) {
        this.service = service;
    }

@GetMapping
    public List<AlocacaoResponse> listar() {
        return null;
        
    }


    @PostMapping
    public ResponseEntity<Alocacao> alocar(@RequestBody AlocacaoRequest req) {
        Alocacao aloc = service.alocar(req.plantaoId(), req.alunoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(aloc);
    }

    @PostMapping("/{id}/checkin")
    public ResponseEntity<Void> checkIn(@PathVariable Long id,
                                        @RequestParam double lat,
                                        @RequestParam double lon) {
        service.checkIn(id, lat, lon);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<Void> checkOut(@PathVariable Long id) {
        service.checkOut(id);
        return ResponseEntity.ok().build();
    }
}