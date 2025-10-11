package dev.flavio.API.Controller;

import java.util.List;
//import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import dev.flavio.API.service.PedidoService;


@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;

//	@PostMapping
//	public ResponseEntity<PedidoDTO> criar(@RequestBody Pedido pedido) {
//		Pedido pedidosalvo = service.save(pedido);
//		return ResponseEntity.ok(PedidoMapper.toDTO(pedidosalvo));
//	}

	 // NOVO ENDPOINT COM DTO
	@PostMapping("/save")
    public ResponseEntity<PedidoDTO> criarComDTO(@RequestBody PedidoCreateDTO pedidoDTO) {
        Pedido pedidoSalvo = service.saveFromDTO(pedidoDTO);
        return ResponseEntity.ok(PedidoMapper.toDTO(pedidoSalvo));
    }


	@GetMapping(value = "/busca-todos")
	public ResponseEntity<List<PedidoDTO>> buscarTodos() {
        List<Pedido> pedidos = service.buscarTodos();
        
        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(pedidosDTO);
    }

	// UPDATE - PUT (substituição completa)
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, 
                                           @RequestBody PedidoCreateDTO pedidoDTO) {
        //Pedido pedidoAtualizado = service.updateFromDTO(id, pedidoDTO);
        Pedido pedidoAtualizado = service.saveFromDTO(pedidoDTO);
        service.delete(id);
        return ResponseEntity.ok(PedidoMapper.toDTO(pedidoAtualizado));
    }

	// UPDATE PARCIAL - PATCH (apenas campos enviados) -> Não está funcionando devido a nova modelagem das tabelas
   
   // @PatchMapping("/{id}")
   // public ResponseEntity<PedidoDTO> partialUpdate(@PathVariable Long id,
   //                                               @RequestBody Map<String, Object> updates) {
        // Implementação para atualização parcial
   //     Pedido pedidoAtualizado = service.partialUpdate(id, updates);
   //     return ResponseEntity.ok(PedidoMapper.toDTO(pedidoAtualizado));
   // }



	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluiPedido(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> buscaPedido(@PathVariable Long id) {
		Pedido pedido = service.findById(id);
		return ResponseEntity.ok().body(pedido);
	}

	
}