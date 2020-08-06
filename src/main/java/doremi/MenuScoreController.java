package doremi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 public class MenuScoreController {
  @Autowired
  MenuScoreRepository pointRepo;
  @PostMapping("/menuscores/save")
  public MenuScore saved(@RequestBody MenuScore postPoint) {

   MenuScore point = new MenuScore();
   point.setId(postPoint.getMenuId());
   point.setMenuId(postPoint.getMenuId());
   point.setScore(0);
   point.setStatus(postPoint.getStatus());

   Optional<MenuScore> score = pointRepo.findById(postPoint.getMenuId());
   if(score!= null&& score.isPresent()){
    System.out.println("##### MenuScore Exist : " +score);

   }else{
    pointRepo.save(point);
    System.out.println("##### MenuScoreRepository saved");
   }


   return point;
  }

  @PostMapping("/menuscores/rate")
  public MenuScore rated(@RequestBody MenuScore postPoint) {

   MenuScore point = new MenuScore();
   Integer ratedPoint = postPoint.getScore();
   if(ratedPoint == null){
    ratedPoint = 0;
   }


   MenuScore score = pointRepo.findByMenuIdAndId(postPoint.getMenuId(), postPoint.getMenuId());
   if(score!=null) {
     point.setId(postPoint.getId());
     point.setMenuId(postPoint.getMenuId());
     point.setStatus("rated");
      point.setScore(score.getScore()+ratedPoint);
      pointRepo.save(point);
    System.out.println("##### MenuScoreRepository rated");


   }
   return point;
  }


  @PostMapping("/menuscores/cancel")
  public MenuScore cancelled(@RequestBody MenuScore postPoint) {

   MenuScore point = new MenuScore();


   MenuScore score = pointRepo.findByMenuIdAndId(postPoint.getMenuId(), postPoint.getMenuId());
   if(score!=null) {
    point.setMenuId(score.getMenuId());
    point.setId(score.getId());
    point.setStatus("cancelled");
    pointRepo.save(point);
    System.out.println("##### MenuScoreRepository cancelled");

   }

   return point;
  }
   @RequestMapping(value = "/menuscores/select/{id}",
         method = RequestMethod.POST,
         produces = "application/json;charset=UTF-8")
 public void select(@PathVariable("id") Long id) {
  System.out.println("##### MenuScoreRepository select"+id);

  Optional<MenuScore> score = pointRepo.findById(id);

  MenuScore printScore =score.get();
   System.out.println("##### MenuScoreRepository id"+printScore.getId());
   System.out.println("##### MenuScoreRepository menuId"+printScore.getMenuId());
   System.out.println("##### MenuScoreRepository score"+printScore.getScore());
   System.out.println("##### MenuScoreRepository Status"+printScore.getStatus());
  }

  }


 }
