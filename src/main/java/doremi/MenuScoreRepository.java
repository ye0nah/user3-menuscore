package doremi;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface MenuScoreRepository extends PagingAndSortingRepository<MenuScore, Long>{

    MenuScore findByMenuIdAndId(Long menuId, Long id);
    List<MenuScore> findByMenuIdOrderByChgDateDesc(Long menuId);




}