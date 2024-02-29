package br.com.josegomes.service;

import br.com.josegomes.dto.request.PersonRequestDTO;
import br.com.josegomes.dto.response.PersonResponseDTO;

import java.util.List;

public interface PersonService {

    PersonResponseDTO findById(Long id);

    List<PersonResponseDTO> findAll();

    PersonResponseDTO register(PersonRequestDTO personDTO);

    PersonResponseDTO update(Long id, PersonRequestDTO personDTO);

    String delete(Long id);

}