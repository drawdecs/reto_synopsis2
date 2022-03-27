package pe.com.cma.apiaccess.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.cma.apiaccess.controller.response.Response;
import pe.com.cma.apiaccess.enumeration.AccessCodeEnum;
import pe.com.cma.apiaccess.service.ClientService;
import pe.com.cma.apiaccess.service.dto.ClientDTO;

import java.util.List;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<ClientDTO>> findById(@PathVariable("id") Long id) {
        ClientDTO client = clientService.findById(id);
        if(client==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new Response<>(AccessCodeEnum.OK.status(),client ));
    }

    @GetMapping
    public ResponseEntity<Response<List<ClientDTO>>> loadAll() {
        return ResponseEntity.ok(new Response<>(AccessCodeEnum.OK.status(), clientService.findAll()));
    }


}
