package br.com.josegomes.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.josegomes.dto.request.PersonRequestDTO;
import br.com.josegomes.dto.response.PersonResponseDTO;
import br.com.josegomes.entity.Person;
import br.com.josegomes.repository.PersonRepository;
import br.com.josegomes.util.PersonMapper;
import lombok.RequiredArgsConstructor;


@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;


    @Override
    public PersonResponseDTO findById(Long id) {
     return personMapper.toPersonDTO(returnPerson(id));
    }

    @Override
    public List<PersonResponseDTO> findAll() {
        return personMapper.toPeopleDTO(personRepository.findAll());
    }

    @Override
    public PersonResponseDTO register(PersonRequestDTO personDTO) {

        Person person = personMapper.toPerson(personDTO);

        return personMapper.toPersonDTO(personRepository.save(person));
    }

    @Override
    public PersonResponseDTO update(Long id, PersonRequestDTO personDTO) {

        Person person = returnPerson(id);

        personMapper.updatePersonData(person,personDTO);

        return personMapper.toPersonDTO(personRepository.save(person));
    }

    @Override
    public String delete(Long id) {
        personRepository.deleteById(id);
        return "Person id: "+id+" deleted";
    }

    private Person returnPerson(Long id) {
       return personRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Person wasn't fount on database"));
    }

}