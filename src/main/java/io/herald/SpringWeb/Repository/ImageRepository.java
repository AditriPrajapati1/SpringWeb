package io.herald.SpringWeb.Repository;

import io.herald.SpringWeb.Model.ImageTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageTable, Integer>
{
   
}
