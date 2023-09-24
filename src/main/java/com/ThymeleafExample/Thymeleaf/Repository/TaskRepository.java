package com.ThymeleafExample.Thymeleaf.Repository;

import com.ThymeleafExample.Thymeleaf.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TaskRepository extends JpaRepository<Task,Long> {

}
