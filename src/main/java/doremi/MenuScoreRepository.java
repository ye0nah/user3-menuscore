package doremi;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface MenuScoreRepository extends PagingAndSortingRepository<MenuScore, Long>{
    MenuScore findByMenuId(Long menuId);
    MenuScore findByMenuIdAndId(Long menuId, Long id);

    List<MenuScore> findByMenuIdOrderByChgDateDesc(Long menuId);

    default Optional<MenuScore> findById(Long id) {
        return null;
    }


}