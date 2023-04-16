import com.demo.ElasticSearchApplication;
import com.demo.dao.HotelRepository;
import com.demo.entity.Hotel;
import com.demo.entity.HotelDoc;
import com.demo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2022-9-29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApplication.class)
public class HotelTest {

    @Resource
    private HotelService hotelService;

    @Resource
    private HotelRepository hotelRepository;

    @Test
    public void createIndex() {
        List<Hotel> hotelList = hotelService.list();
        for (Hotel hotel : hotelList) {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            hotelRepository.save(hotelDoc);
            System.out.println(hotel.getId());
        }
    }


}
