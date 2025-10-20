package dev.flavio.API.Controller;

import java.util.List;
//import java.util.Map;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.flavio.API.Entity.Pedido;
import dev.flavio.API.Mapper.PedidoMapper;
import dev.flavio.API.dto.PedidoCreateDTO;
import dev.flavio.API.dto.PedidoDTO;
import dev.flavio.API.exceptions.ResourceNotFoundException;
import dev.flavio.API.service.PedidoService;


@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

	
	private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }


	@PostMapping("/save")
    public <Optional> ResponseEntity<PedidoDTO> criarComDTO(@RequestBody PedidoCreateDTO pedidoDTO) throws BadRequestException {
        try{
        PedidoDTO pedidoSalvo = service.saveFromDTO(pedidoDTO);
        return ResponseEntity.ok(pedidoSalvo); }
        catch (Exception e) {
            throw new BadRequestException("Erro na criação do Pedido - Verifique campos obrigatórios");
        }
    }


	@GetMapping(value = "/busca-todos")
	public ResponseEntity<List<PedidoDTO>> buscarTodos() {
        try {
        List<Pedido> pedidos = service.buscarTodos();
        
        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(pedidosDTO);    
        }
        catch(Exception e) {
             throw new ResourceNotFoundException("Error searching tasks with the provided filters");
        }
    }

	// UPDATE - PUT (substituição completa)
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, 
                                           @RequestBody PedidoCreateDTO pedidoDTO) throws Exception {
        
        if(service.findById(id) == null){throw new ResourceNotFoundException("pedido não encontrado");}
        try {
        PedidoDTO pedidoAtualizado = service.saveFromDTO(pedidoDTO);
        service.delete(id);
        return ResponseEntity.ok(pedidoAtualizado);}
        catch(Exception e) {
           throw new BadRequestException("Erro na alteração do Pedido - Verifique campos obrigatórios"); 
        }
    }

	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluiPedido(@PathVariable Long id)  throws Exception {
		if(service.findById(id) == null){throw new ResourceNotFoundException("pedido não encontrado");}
        try {
        service.delete(id);
		return ResponseEntity.noContent().build();}
        catch (Exception e) {
        throw new ResourceNotFoundException("Error searching tasks with the provided filters");
        }
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> buscaPedido(@PathVariable Long id) {
		Pedido pedido = service.findById(id);
		return ResponseEntity.ok().body(pedido);
	}

	
}