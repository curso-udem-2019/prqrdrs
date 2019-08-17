/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udem.prqrdrs.dao;

import edu.udem.prqrdrs.entities.PersonaEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author jpramirez
 */
@Transactional
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    PersonaEntity findByLogin(String login);
} 

