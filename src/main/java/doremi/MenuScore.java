package doremi;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MenuScore_table")
public class MenuScore {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long menuId;
    private Integer score;
    private String status;
    private Date chgDate;


    public MenuScore() {
        this.score = 0;
        Date date = new Date();
        this.chgDate = new Timestamp(date.getTime());
    }

    @PostPersist
    public void onPostPersist(){

        if(this.getStatus().equals("saved")){
            System.out.println("##### MenuScore onPostPersist saved ");
            Saved saved = new Saved();
            BeanUtils.copyProperties(this, saved);
            saved.publishAfterCommit();

        }else if(this.getStatus().equals("rated")){
            System.out.println("##### MenuScore onPostPersist rated ");
            Rated rated = new Rated();
            BeanUtils.copyProperties(this, rated);
            rated.publishAfterCommit();
        }else if(this.getStatus().equals("cancelled")){
            System.out.println("##### MenuScore onPostPersist cancelled ");
            Cancelled cancelled = new Cancelled();
            BeanUtils.copyProperties(this, cancelled);
            cancelled.publishAfterCommit();
        }


    }

    @PostUpdate
    public void onPostUpdate(){
        Cancelled cancelled = new Cancelled();
        BeanUtils.copyProperties(this, cancelled);
        cancelled.publishAfterCommit();


    }

    @PostRemove
    public void onPostRemove(){
        Cancelled cancelled = new Cancelled();
        BeanUtils.copyProperties(this, cancelled);
        cancelled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getChgDate() {
        return chgDate;
    }

    public void setChgDate(Date chgDate) {
        this.chgDate = chgDate;
    }




}
