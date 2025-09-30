package com.du.em0930.service;

import com.du.em0930.entity.Emp;
import com.du.em0930.repository.EmpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository empRepository;

    public void save(Emp emp){
        empRepository.save(emp);
    }

    public Emp getById(Long id){
        return empRepository.findById(id);
    }

    public List<Emp> getAll(){
        return empRepository.findAll();
    }

    public void update(Emp emp){
        empRepository.update(emp);
    }

    public void delete(Long id){
        empRepository.delete(id);
    }
}
