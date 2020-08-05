package doremi;

import doremi.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler{


    @Autowired
    MenuScoreController pc;

    @Autowired
    MenuScoreRepository pointRepo;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMenuDeleted_DeleteScore(@Payload MenuDeleted menuDeleted){

        if(menuDeleted.isMe()){
            System.out.println("##### listener DeleteScore : " + menuDeleted.toJson());
            MenuScore point = new MenuScore();

            List<MenuScore> pointList = pointRepo.findByMenuIdOrderByChgDateDesc(menuDeleted.getId());

            if(!pointList.isEmpty()){
                point.setId(pointList.get(0).getId());
                point.setMenuId(pointList.get(0).getId());
                pc.cancelled(point);
                System.out.println("cancelled:"+point);
            }


        }
    }

}
