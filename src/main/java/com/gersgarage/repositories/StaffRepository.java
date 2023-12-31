package com.gersgarage.repositories;

import com.gersgarage.models.Client;
import com.gersgarage.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query("SELECT s FROM Staff s")
    List<Staff> findAllStaff();

    @Query("SELECT s FROM Staff s WHERE s.id_staff = :staffId")
    List<Staff> findStaffById(@Param("staffId") Integer staffId);

    @Query("SELECT c FROM Client c WHERE c.id_client = :clientId")
    List<Client> findClientById(@Param("clientId") Integer clientId);

    @Query("SELECT s FROM Staff s WHERE s.staff_fname = :firstName")
    List<Staff> findStaffByFirstName(@Param("firstName") String firstName);

    @Query("SELECT s FROM Staff s WHERE s.staff_lname = :lastName")
    List<Staff> findStaffByLastName(@Param("lastName") String lastName);

    @Modifying
    @Query("DELETE FROM Staff s WHERE s.id_staff = :staffId")
    void deleteStaffById(@Param("staffId") Integer staffId);



}
