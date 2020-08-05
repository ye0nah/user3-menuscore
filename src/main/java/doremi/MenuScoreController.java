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
  @PostMapping("/menuscore/save")
  public MenuScore saved(@RequestBody MenuScore postPoint) {

   MenuScore point = new MenuScore();
   point.setId(postPoint.getMenuId());
   point.setMenuId(postPoint.getMenuId());
   point.setScore(0);
   point.setStatus(postPoint.getStatus());

   Optional<MenuScore> score = pointRepo.findById(postPoint.getMenuId());
   if(score!= null){
    System.out.println("##### MenuScore Exist : " );

   }else{
    pointRepo.save(point);
    System.out.println("##### MenuScoreRepository saved");
   }


   return point;
  }

  @PostMapping("/menuscore/rate")
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


  @PostMapping("/menuscore/cancel")
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

 }
