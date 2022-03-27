package pe.com.cma.apiaccess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.cma.apiaccess.dao.model.Client;
import pe.com.cma.apiaccess.repository.ClientRepository;
import pe.com.cma.apiaccess.service.ClientService;
import pe.com.cma.apiaccess.service.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientDTO findById(Long id) {
        Optional<Client> o =clientRepository.findById(id);

        ClientDTO clientDTO = new ClientDTO();
        if(o.isPresent()){
            Client c = o.get();
            return map(c,clientDTO);
        }else{
            return null;
        }

    }

    private ClientDTO map(Client c, ClientDTO clientDTO) {
        clientDTO.setClientId(c.getClientId());
        clientDTO.setName(c.getName());
        clientDTO.setPhone(c.getPhone());
        clientDTO.setEmail(c.getMail());
        return clientDTO;
    }

    @Override
    public List<ClientDTO> findAll() {
        List<ClientDTO> result = new ArrayList<>();
        clientRepository.findAll().forEach(d->{ result.add(map(d,new ClientDTO()));});

        return result;
    }


}
