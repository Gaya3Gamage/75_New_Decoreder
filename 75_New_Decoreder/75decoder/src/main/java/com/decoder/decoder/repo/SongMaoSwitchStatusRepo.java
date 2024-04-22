
package com.decoder.decoder.repo;

import com.decoder.decoder.Entity.SongMaoSwitchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SongMaoSwitchStatusRepo extends JpaRepository<SongMaoSwitchStatus,Long> {
}
